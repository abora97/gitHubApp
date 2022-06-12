package com.abora.githubtask.data.remote

import com.abora.githubtask.data.models.UserRepositoriesDataModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitApi {

    @GET("search/repositories")
    fun getUsers(@Query("q") searchWord:String?,@Query("page") page:Int?,@Query("per_page")perPage:Int): Deferred<Response<UserRepositoriesDataModel>>

}