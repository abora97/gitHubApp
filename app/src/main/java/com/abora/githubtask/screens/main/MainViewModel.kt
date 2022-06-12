package com.abora.githubtask.screens.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abora.githubtask.base.BaseViewModel
import com.abora.githubtask.data.models.UserRepositoriesData
import com.abora.githubtask.data.remote.reporsitory.MainRepository
import kotlinx.coroutines.launch


class MainViewModel constructor(var mainRepository: MainRepository, val context: Context) :
    BaseViewModel() {


    //Responses
    var getUserDataResponse = MutableLiveData<List<UserRepositoriesData>>()


    //pagenation
    var isloading: Boolean = false
    var lastPage: Boolean = false


    fun getUsers(searchWord: String,page:Int) {
        loading.value = true
        isloading = false
        viewModelScope.launch {
            mainRepository.getUsers(networkStatus, searchWord,page).let {
                loading.value = false
                isloading = false
                lastPage = if (it.data != null){
                    getUserDataResponse.postValue(it.data.userRepositoriesData)
                    false
                }else{
                    true
                }
            }
        }
    }

}