package com.example.hbapplicationgroupb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hbapplicationgroupb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //var of type of binding class created for xml file
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)//set binding to root layout
    }
}