package com.abora.githubtask.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.abora.githubtask.R
import com.abora.githubtask.data.remote.networkHandling.NetworkStatus
import com.abora.githubtask.utils.MyUtils.myToast
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<T : ViewDataBinding ,  V : BaseViewModel> : Fragment(), NetworkStatus {


    private var appContext: Context? = null

    val viewModel: V by lazy { getViewModel(viewModelClass()) }

    val sharedPreferences: SharedPreferences by inject()

    lateinit var dataBinding: T

    abstract fun layoutResource(): Int


    var dialog: AlertDialog? = null
    abstract fun viewModelClass(): KClass<V>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutResource(), container, false)

        dataBinding.lifecycleOwner = this
        appContext = context
        viewModel.networkStatus = this
        dataBinding.lifecycleOwner = this

        baseObserver()

        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI(savedInstanceState)

        clicks()
        callApis()
        observer()
    }

    private fun baseObserver(){

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            toggleLoadingDialog(it)
        })

        viewModel.showMassage.observe(viewLifecycleOwner, Observer {
            myToast(it)
        })

    }

    abstract fun setUI(savedInstanceState: Bundle?)
    abstract fun observer()
    abstract fun clicks()
    abstract fun callApis()

    override fun onResume() {
        super.onResume()
        appContext = context
    }



    fun toggleLoadingDialog(show: Boolean) {

        if (dialog == null) {
            dialog = AlertDialog.Builder(requireContext())
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


    override fun onNoInternet() {
        (requireActivity() as BaseActivity<*, *>).onNoInternet()
    }

    override fun onApiNotFound() {

        (requireActivity() as BaseActivity<*, *>).onApiNotFound()
    }

    override fun onNotVerifyRequest(exception: String?) {

    }

    override fun onNotAllowed() {

        (requireActivity() as BaseActivity<*, *>).onNotAllowed()
    }

    override fun onNotAuthorized(exception: String?) {
        (requireActivity() as BaseActivity<*, *>).onNotAuthorized(exception)
    }

    override fun onServerSideError(exception: String?) {
        (requireActivity() as BaseActivity<*, *>).onServerSideError(exception)
    }

    override fun onBadRequest(exception: String?) {
        (requireActivity() as BaseActivity<*, *>).onBadRequest(exception)

    }


}
