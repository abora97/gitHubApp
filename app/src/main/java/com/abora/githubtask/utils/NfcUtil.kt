package com.abora.githubtask.utils

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.util.Log
import android.widget.Toast

object NfcUtil {

    fun <T> enableNFCInForeground(nfcAdapter: NfcAdapter, activity: Activity, classType: Class<T>) {
        if (nfcAdapter != null){
            val pendingIntent = PendingIntent.getActivity(
                activity, 0,
                Intent(activity, classType).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
            )
            nfcAdapter.enableForegroundDispatch(activity, pendingIntent, null, null)
        }else{
            Toast.makeText(activity, "nfc not support your device.", Toast.LENGTH_LONG).show()
        }
    }

    fun disableNFCInForeground(nfcAdapter: NfcAdapter, activity: Activity) {
        if (nfcAdapter != null)
            nfcAdapter.disableForegroundDispatch(activity)
    }

    fun getDataFromTag(intent: Intent?): MutableList<String>? {
        val listNfcData : MutableList<String> = arrayListOf()
        val nfcData: Tag? = intent?.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        val ndef = Ndef.get(nfcData)
        try {
            ndef.connect()
            val messages = intent?.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            if (messages != null) {
                val ndefMessages = arrayOfNulls<NdefMessage>(messages.size)
                for (i in messages.indices) {
                    ndefMessages[i] = messages[i] as NdefMessage
                    ndefMessages[i]!!.records.forEach {
                        listNfcData.add(
                            if (String(it.payload).startsWith("\u0002en")){
                                String(it.payload).drop(3)
                            }else{
                                String(it.payload)
                            }
                            )
                    }
                }
                Log.e("tag", listNfcData.toString())
                ndef.close()

            }
            return listNfcData
        } catch (e: Exception) {
            Log.e("tag", "Cannot Read From Tag.")
            return null
        }
    }
}