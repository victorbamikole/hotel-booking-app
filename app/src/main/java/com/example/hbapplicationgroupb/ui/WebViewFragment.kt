package com.example.hbapplicationgroupb.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentWebViewBinding
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment(R.layout.fragment_web_view) {
    private lateinit var binding:FragmentWebViewBinding
    private val roomViewModel: RoomViewModel by viewModels()
    private  val webViewSaveArgs: WebViewFragmentArgs by navArgs()
    private lateinit var saveUrl:String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWebViewBinding.bind(view)
        val webToLoadurl = webViewSaveArgs.url


        binding.hotelBookingWebView.apply {
            webViewClient = object :WebViewClient(){
                override fun onLoadResource(view: WebView?, url: String?) {
                    super.onLoadResource(view, url)

                    if (Uri.parse(url).host!!.contains("www.example.com")) {
                        val action = WebViewFragmentDirections
                            .actionWebViewFragmentToBookingConfirmationFragment(Uri.parse(url).host!!)
                        findNavController().navigate(action)
                        roomViewModel.listener.value = Uri.parse(url).host!!
                        saveUrl = Uri.parse(url).host!!
                        findNavController().navigate(action)
                    }
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (Uri.parse(url).host!!.contains("www.example.com")) {
                        val action = WebViewFragmentDirections
                            .actionWebViewFragmentToBookingConfirmationFragment(Uri.parse(url).host!!)
                        findNavController().navigate(action)
                        roomViewModel.listener.value = Uri.parse(url).host!!
                        saveUrl = Uri.parse(url).host!!

                        findNavController().navigate(action)
                    }
                    return super.shouldOverrideUrlLoading(view, url)
                }
            }
            loadUrl(webToLoadurl)
        }
        roomViewModel.listener.observe(viewLifecycleOwner,{
            if (it!=null){
                val action = WebViewFragmentDirections
                    .actionWebViewFragmentToBookingConfirmationFragment(it)
                findNavController().navigate(action)
            }
        })


        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val action = WebViewFragmentDirections
                    .actionWebViewFragmentToBookingConfirmationFragment(saveUrl)
                findNavController().navigate(action)
            }
        })

    }
//


}