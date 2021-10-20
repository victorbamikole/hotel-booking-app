package com.example.hbapplicationgroupb.util.resource

sealed class Resource<T>(
    val data : T? = null,
val message : String? = null
){
    class Loading<T> : Resource<T>()
    class Success<T>(data:T) : Resource<T>(data)
    class Error<Nothing>(val exception: String) : Resource<Nothing>()
}
