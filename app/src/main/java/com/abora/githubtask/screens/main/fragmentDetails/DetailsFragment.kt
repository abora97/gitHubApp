package com.abora.githubtask.screens.main.fragmentDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abora.githubtask.R
import com.abora.githubtask.base.BaseFragment
import com.abora.githubtask.databinding.FragmentDetailsBinding
import com.abora.githubtask.screens.main.MainViewModel
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<FragmentDetailsBinding,MainViewModel>() {
    override fun layoutResource(): Int = R.layout.fragment_details

    override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {

    }

    override fun observer() {

    }

    override fun clicks() {

    }

    override fun callApis() {

    }

}