package com.abora.githubtask.screens.main

import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.abora.githubtask.R
import com.abora.githubtask.base.BaseActivity
import com.abora.githubtask.databinding.ActivityMainBinding
import com.abora.githubtask.utils.MyUtils.myToastAction2Button
import com.abora.githubtask.utils.NfcUtil
import kotlin.reflect.KClass

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var nfcAdapter: NfcAdapter

    lateinit var navController: NavController

    override fun resourceId(): Int = R.layout.activity_main

    override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {
        navController = findNavController(R.id.hostFragment)
        if (NfcAdapter.getDefaultAdapter(this) != null)
            nfcAdapter = NfcAdapter.getDefaultAdapter(this)

    }

    override fun observer() {

    }

    override fun clicks() {

    }

    override fun callApis() {

    }

    override fun onBackPressed() {
        if (navController.graph.startDestinationId == navController.currentDestination?.id) {
            myToastAction2Button("Do you want to exit the application?") { alertDialog ->
                alertDialog.dismiss()
                finishAffinity()
            }
        } else {
            super.onBackPressed()
            overridePendingTransition(R.anim.no_change, R.anim.slide_down)
        }
    }


    override fun onResume() {
        super.onResume()
        if (NfcAdapter.getDefaultAdapter(this) != null)
            NfcUtil.enableNFCInForeground(nfcAdapter, this, javaClass)
    }

    override fun onPause() {
        super.onPause()
        if (NfcAdapter.getDefaultAdapter(this) != null)
            NfcUtil.disableNFCInForeground(nfcAdapter, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (NfcUtil.getDataFromTag(intent).isNullOrEmpty().not()){
            intent?.putExtra("nfcData",true)
            setIntent(intent)
        }

    }


}