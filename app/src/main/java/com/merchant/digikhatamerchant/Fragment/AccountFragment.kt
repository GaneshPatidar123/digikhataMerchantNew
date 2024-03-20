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
import com.merchant.digikhatamerchant.Activities.AddBankActivity
import com.merchant.digikhatamerchant.Activities.ChangePasswordActivity
import com.merchant.digikhatamerchant.Activities.MainActivity
import com.merchant.digikhatamerchant.Activities.ProfileActivity
import com.merchant.digikhatamerchant.Activities.WebViewActivity
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.DialogLogoutLayoutBinding
import com.merchant.digikhatamerchant.databinding.DialogSearchTransactionLayoutBinding
import com.merchant.digikhatamerchant.databinding.FragmentAccountBinding
import com.merchant.digikhatamerchant.databinding.FragmentTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>(R.layout.fragment_account) {

    override fun setUpViews() {
        super.setUpViews()
    }

    override fun setUpListeners() {
        binding.ivNext.setOnClickListener {
            (activity as MainActivity).launch(ProfileActivity::class.java)
        }
        binding.ivNextchangepassword.setOnClickListener {
            (activity as MainActivity).launch(ChangePasswordActivity::class.java)
        }
        binding.ivNexttermsandconditions.setOnClickListener {
            (activity as MainActivity).launch(WebViewActivity::class.java)
        }
        binding.ivNextaddbankaccount.setOnClickListener {
            (activity as MainActivity).launch(AddBankActivity::class.java)
        }
        binding.ivNextLogout.setOnClickListener {
            dialogLogOut()
        }
        binding.AccountscrrenLogout.setOnClickListener {
            dialogLogOut()
        }
        binding.tvProfileAccountscreen.setOnClickListener {
            (activity as MainActivity).launch(ProfileActivity::class.java)
        }
        binding.Accountscrrentermsanduses.setOnClickListener {
            (activity as MainActivity).launch(ChangePasswordActivity::class.java)
        }
        binding.AccountscrrenAddbankaccount.setOnClickListener {
            (activity as MainActivity).launch(WebViewActivity::class.java)
        }
        binding.Accountscrrenchangepassword.setOnClickListener {
            (activity as MainActivity).launch(AddBankActivity::class.java)
        }

    }

    private fun dialogLogOut() {
        val dialog = Dialog(requireActivity(), R.style.RoundedCornersDialog)
        val mBottomSheetBinding = DialogLogoutLayoutBinding.inflate(layoutInflater, null, false)
        dialog.setContentView(mBottomSheetBinding.root)
        val window: Window? = dialog.window
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        dialog.setCancelable(false)
        mBottomSheetBinding.btnLogout.setOnClickListener {
            dialog.dismiss()
        }
        mBottomSheetBinding.tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun setUpObservers() {
        super.setUpObservers()
    }
}