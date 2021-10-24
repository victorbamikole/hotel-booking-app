package com.example.hbapplicationgroupb.util.resource

sealed class ApiCallNetworkResource<T>(
    val data : String? = null,
    val message : String? = null

){
    class Success<T>(data: String) : ApiCallNetworkResource<T>(data)
    class Error<T>(message: String, data: String? = null) : ApiCallNetworkResource<T>(data,message)
    class Loading<T>: ApiCallNetworkResource<T>()

}
