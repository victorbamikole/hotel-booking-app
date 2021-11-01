package com.example.hbapplicationgroupb.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.repository.ApiRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HotelBookingWorkManagerClass(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams){
//    @Inject
//    lateinit var repositoryInterface: ApiRepositoryInterface
//    private val userId = context.let { UserPreferences(it).getUserId() }
//    private val userRefreshToken = context.let { UserPreferences(it).getUserRefreshToken() }
    override fun doWork(): Result {
//        GlobalScope.launch(Dispatchers.IO){
//            val response = repositoryInterface.refreshTokenRequest(userId,userRefreshToken)
//            if (response.isSuccessful){
//                withContext(Dispatchers.Main){
//                    Toast.makeText(applicationContext, "successful", Toast.LENGTH_SHORT).show()
//                }
//            }else{
//                withContext(Dispatchers.Main){
//                    Toast.makeText(applicationContext, "failed", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
        Log.d("doWork", "doWork: called")
        return Result.retry()
    }
}