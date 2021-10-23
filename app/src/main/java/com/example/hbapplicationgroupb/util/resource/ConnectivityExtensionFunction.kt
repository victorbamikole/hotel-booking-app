package com.example.hbapplicationgroupb.util.resource

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.qualifiers.ApplicationContext

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