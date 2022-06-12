package com.abora.githubtask.data.remote.reporsitory

import com.abora.githubtask.data.remote.RetrofitApi
import com.abora.githubtask.data.remote.networkHandling.NetworkResult
import com.abora.githubtask.data.remote.networkHandling.NetworkStatus


class MainRepository constructor(var apiService: RetrofitApi) : NetworkResult() {

    suspend fun getUsers(networkStatus: NetworkStatus, searchWord:String,page:Int,perPage:Int=20) = getResult({
        apiService.getUsers(searchWord,page,perPage).await()
    }, networkStatus)

}