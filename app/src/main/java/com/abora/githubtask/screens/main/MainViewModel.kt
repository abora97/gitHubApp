package com.abora.githubtask.screens.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abora.githubtask.base.BaseViewModel
import com.abora.githubtask.data.models.UserRepositoriesData
import com.abora.githubtask.data.remote.reporsitory.MainRepository
import kotlinx.coroutines.launch


class MainViewModel constructor(var mainRepository: MainRepository) :
    BaseViewModel() {

    //Fields
    val searchWordLiveData=MutableLiveData<String>()
    var pageLiveData=MutableLiveData<Int>().apply {
        value = 1
    }

    //Responses
    var getUserDataResponse = MutableLiveData<List<UserRepositoriesData>>()


    //pagination
    var isloading: Boolean = false
    var lastPage: Boolean = false


    fun getUsers() {

        if (searchWordLiveData.value.isNullOrBlank()){
            return
        }

        loading.value = true
        isloading = true
        viewModelScope.launch {
            mainRepository.getUsers(networkStatus, searchWordLiveData.value,pageLiveData.value).let {
                loading.value = false
                isloading = false
                lastPage = if (it.data != null){
                    getUserDataResponse.value=it.data.userRepositoriesData
                    false
                }else{
                    true
                }
            }
        }
    }

}