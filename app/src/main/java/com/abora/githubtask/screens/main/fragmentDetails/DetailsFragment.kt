package com.abora.githubtask.screens.main.fragmentDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abora.githubtask.R
import com.abora.githubtask.base.BaseFragment
import com.abora.githubtask.data.models.UserRepositoriesData
import com.abora.githubtask.databinding.FragmentDetailsBinding
import com.abora.githubtask.screens.main.MainViewModel
import com.abora.githubtask.utils.MyUtils.openBrowser
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_details.*
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<FragmentDetailsBinding,MainViewModel>() {

    lateinit var userRepositoriesData: UserRepositoriesData

    override fun layoutResource(): Int = R.layout.fragment_details

    override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {
        userRepositoriesData= Gson().fromJson(arguments?.getString("data"),UserRepositoriesData::class.java)
        dataBinding.data=userRepositoriesData
    }

    override fun observer() {

    }

    override fun clicks() {
        btnOpenRepo.setOnClickListener {
            openBrowser(userRepositoriesData.html_url)
        }
    }

    override fun callApis() {

    }

}