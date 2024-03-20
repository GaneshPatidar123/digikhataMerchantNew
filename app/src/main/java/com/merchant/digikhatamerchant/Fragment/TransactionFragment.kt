package com.merchant.digikhatamerchant.Fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatEditText
import com.digikhata.merchant.Base.BaseFragment
import com.example.paypointretailer.Utils.AppUtils
import com.google.android.material.tabs.TabLayoutMediator
import com.merchant.digikhatamerchant.Activities.MainActivity
import com.merchant.digikhatamerchant.Activities.TransactionDetailsActivity
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.DialogSearchTransactionLayoutBinding
import com.merchant.digikhatamerchant.databinding.DialogSettlementLayoutBinding
import com.merchant.digikhatamerchant.databinding.FragmentTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment :
    BaseFragment<FragmentTransactionBinding>(R.layout.fragment_transaction),
    BaseFragment.onDateSelect {
    private lateinit var mBottomSheetBinding: DialogSettlementLayoutBinding
    private lateinit var searchDialog: DialogSearchTransactionLayoutBinding
    override fun setUpViews() {
        AppUtils.setActivtiy(requireActivity())
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Transaction"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Refunds"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Charge Back"))

    }

    override fun setUpListeners() {
        // super.setUpListeners()


        binding.btnSettlement.setOnClickListener {
            showDialog()
        }
        binding.edtSearch.setOnClickListener {
            showSearchDialog()
        }
        binding.one.root.setOnClickListener {
            (activity as MainActivity).launch(TransactionDetailsActivity::class.java)
        }
    }

    private fun showSearchDialog() {
        val dialog = Dialog(requireActivity(), R.style.RoundedCornersDialog)
        searchDialog = DialogSearchTransactionLayoutBinding.inflate(layoutInflater, null, false)
        dialog.setContentView(searchDialog.root)
        val window: Window? = dialog.window
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        dialog.setCancelable(false)

        searchDialog.edtFromDate.setOnClickListener {
            showDatePicker(this, searchDialog.edtFromDate)
        }
        searchDialog.edtToDate.setOnClickListener {
            if (!searchDialog.edtFromDate.text.toString().isNullOrEmpty()) {
                showDatePickerTodate(
                    this,
                    searchDialog.edtToDate,
                    searchDialog.edtFromDate.text.toString()
                )
            }
        }
        searchDialog.btnSearch.setOnClickListener {
            dialog.dismiss()
        }
        searchDialog.tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showDialog() {
        val dialog = Dialog(requireActivity(), R.style.RoundedCornersDialog)
        mBottomSheetBinding =
            DialogSettlementLayoutBinding.inflate(layoutInflater, null, false)
        dialog.setContentView(mBottomSheetBinding.root)
        val window: Window? = dialog.window
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        dialog.setCancelable(false)
        mBottomSheetBinding.radioGroupLayout.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonBank -> {
                    mBottomSheetBinding.TvBankAccountnumbersettelment.text = "Bank Acc No: "
                    mBottomSheetBinding.edtChooseBankName.visibility = View.VISIBLE
                    mBottomSheetBinding.TvBankAccountnumbersettelmentAns.setText("12376458763546")
                }

                R.id.radioButtonWallet -> {
                    mBottomSheetBinding.TvBankAccountnumbersettelment.text = "Wallet Number:"
                    mBottomSheetBinding.edtChooseBankName.visibility = View.GONE
                    mBottomSheetBinding.TvBankAccountnumbersettelmentAns.setText("6576557657657557575")

                }

            }
        }
        mBottomSheetBinding.btnContinous.setOnClickListener {
            dialog.dismiss()
        }
        mBottomSheetBinding.ivClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun setUpObservers() {
        super.setUpObservers()
    }

    override fun date(date: String, editText: AppCompatEditText?) {
        editText?.setText(date)
    }
}