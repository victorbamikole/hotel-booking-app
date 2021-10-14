package com.example.hbapplicationgroupb.ui.paymentcheckout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentPaymentCheckoutBinding

class PaymentCheckoutFragment : Fragment(R.layout.fragment_payment_checkout) {

    var binding: FragmentPaymentCheckoutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentCheckoutBinding.bind(view)
    }

}