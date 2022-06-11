package com.abora.githubtask.base

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.abora.githubtask.R
import com.abora.githubtask.data.remote.networkHandling.NetworkStatus
import com.abora.githubtask.utils.MyUtils.myToast
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import java.util.*
import kotlin.reflect.KClass

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(),
    NetworkStatus {

    lateinit var dataBinding: T

    val viewModel: V by lazy { getViewModel(viewModelClass()) }

    val sharedPreferences: SharedPreferences by inject()

    var dialog: AlertDialog? = null

    abstract fun resourceId(): Int

    abstract fun viewModelClass(): KClass<V>


    override fun onCreate(savedInstanceState: Bundle?) {
        setAppMode()
        super.onCreate(savedInstanceState)
        init()

        baseObserver()

        setUI(savedInstanceState)
        clicks()
        callApis()
        observer()

    }

    private fun init(){

        viewModel.networkStatus = this
        dataBinding = DataBindingUtil.setContentView(this, resourceId())
        dataBinding.lifecycleOwner = this

    }

    private fun baseObserver() {

        viewModel.loading.observe(this, Observer {
            toggleLoadingDialog(it)
        })

        viewModel.showMassage.observe(this, Observer {
            myToast(it)
        })

    }


    abstract fun setUI(savedInstanceState: Bundle?)
    abstract fun observer()
    abstract fun clicks()
    abstract fun callApis()



    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_change, R.anim.slide_down)

    }



    private fun setAppMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onNoInternet() {

    }

    override fun onNotVerifyRequest(exception: String?) {

    }

    override fun onApiNotFound() {

    }

    override fun onNotAllowed() {

    }

    override fun onServerSideError(exception: String?) {
        myToast(exception.toString())
    }

    override fun onBadRequest(exception: String?) {
        myToast(exception.toString())
    }

    fun toggleLoadingDialog(show: Boolean) {

        if (dialog == null) {
            dialog = AlertDialog.Builder(this)
                .setView(R.layout.progress)
                .setCancelable(false)
                .create()

            dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }

        if (!show)
            dialog?.dismiss()
        else if (show)
            dialog?.show()


    }

    override fun onPause() {
        super.onPause()
        dialog?.dismiss()
    }

    override fun onStop() {
        super.onStop()
        dialog?.dismiss()
    }

    fun checkWriteToStoragePermission(): Boolean {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        val res: Int = checkCallingOrSelfPermission(permission)
        return res == PackageManager.PERMISSION_GRANTED
    }





}