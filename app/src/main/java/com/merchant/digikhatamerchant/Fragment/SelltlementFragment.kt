package com.merchant.digikhatamerchant.Fragment

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.digikhata.merchant.Base.BaseFragment
import com.merchant.digikhatamerchant.Activities.MainActivity
import com.merchant.digikhatamerchant.Activities.SettlementDetailActivity
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.DialogSearchTransactionLayoutBinding
import com.merchant.digikhatamerchant.databinding.FragmentSettlementBinding
import com.merchant.digikhatamerchant.databinding.FragmentTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelltlementFragment : BaseFragment<FragmentSettlementBinding>(R.layout.fragment_settlement)  {
    override fun setUpViews() {
        super.setUpViews()
    }

    override fun setUpListeners() {
        binding.edtSearch.setOnClickListener {
            showSearchDialog()
        }
        binding.one.root.setOnClickListener {
            (activity as MainActivity).launch(SettlementDetailActivity::class.java)
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
    }
    private fun showSearchDialog() {
        val dialog = Dialog(requireActivity(), R.style.RoundedCornersDialog)
        val mBottomSheetBinding = DialogSearchTransactionLayoutBinding.inflate(layoutInflater, null, false)
        dialog.setContentView(mBottomSheetBinding.root)
        val window: Window? = dialog.window
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        dialog.setCancelable(false)
        mBottomSheetBinding.btnSearch.setOnClickListener {
            dialog.dismiss()
        }
        mBottomSheetBinding.tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }



}