package com.example.hbapplicationgroupb.util.resource


import androidx.lifecycle.LifecycleOwner

fun observeNetworkConnection(connectivityLiveData:ConnectivityLiveData, context: LifecycleOwner, funcWhenTrue:()->Unit,funcWhenFalse:()->Unit){
    connectivityLiveData.observe(context,{ isAvailable ->
        //check if network is available
        when (isAvailable) {
            true -> {
                funcWhenTrue()
            }
            false -> {

                funcWhenFalse()

            }
        }
    })
}