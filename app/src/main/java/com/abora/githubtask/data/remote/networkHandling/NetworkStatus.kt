package com.abora.githubtask.data.remote.networkHandling

interface NetworkStatus {
    fun onBadRequest(exception: String?)
    fun onNotVerifyRequest(exception: String?)
    fun onNotAuthorized(exception: String?)
    fun onServerSideError(exception: String?)
    fun onNotAllowed()
    fun onApiNotFound()
    fun onNoInternet()
}