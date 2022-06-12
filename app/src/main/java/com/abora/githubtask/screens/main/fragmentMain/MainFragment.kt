package com.abora.githubtask.screens.main.fragmentMain

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.abora.githubtask.R
import com.abora.githubtask.base.BaseFragment
import com.abora.githubtask.data.models.UserRepositoriesData
import com.abora.githubtask.databinding.FragmentMainBinding
import com.abora.githubtask.screens.main.MainViewModel
import com.abora.githubtask.utils.NfcUtil
import com.abora.githubtask.utils.PaginationListener
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.reflect.KClass

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(),
    UserDataAdapter.UserAction {

    private lateinit var userDataAdapter: UserDataAdapter

    override fun layoutResource(): Int = R.layout.fragment_main

    override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {

        userDataAdapter = UserDataAdapter(this)
        recUsers.adapter = userDataAdapter
    }

    override fun observer() {
        viewModel.getUserDataResponse.observe(this) {
            if (viewModel.pageLiveData.value == 1) {
                userDataAdapter.setDate(it)
            } else {
                userDataAdapter.updateDate(it)
            }
        }
    }

    override fun clicks() {
        edSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchWordLiveData.value = edSearch.text.toString()
                viewModel.pageLiveData.value = 1
                callApis()
                return@OnEditorActionListener true
            }
            false
        })

        recUsers.addOnScrollListener(object :
            PaginationListener(recUsers.layoutManager as LinearLayoutManager) {
            override val isLastPage: Boolean
                get() = viewModel.lastPage
            override val isLoading: Boolean
                get() = viewModel.isloading

            override fun loadMoreItems() {
                viewModel.isloading = true
                viewModel.pageLiveData.value = viewModel.pageLiveData.value?.plus(1)
                callApis()
            }

        })

    }

    override fun callApis() {
        viewModel.getUsers()
    }

    override fun onUserClick(item: UserRepositoriesData, view: View) {
        val bundle = Bundle()
        bundle.putInt("id", 0)
        Navigation.findNavController(view)
            .navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().intent?.let {
                if (it.extras != null) {
                    viewModel.searchWordLiveData.value = NfcUtil.getDataFromTag(it)?.get(0)?.trim()
                    viewModel.pageLiveData.value = 1
                    viewModel.getUsers()
                }
        }
    }


}