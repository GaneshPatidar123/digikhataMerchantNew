package com.merchant.digikhatamerchant.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.TextView
import com.digikhata.merchant.Base.BaseActivity
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.ActivitySettlementDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettlementDetailActivity :
    BaseActivity<ActivitySettlementDetailBinding>(R.layout.activity_settlement_detail) {
    private var tooltipPopup: PopupWindow? = null
    override fun setUpViews() {
        binding.toolBar.tvTitle.text = getString(R.string.settlement_Details)
    }

    override fun setUpListeners() {
        binding.toolBar.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.transactionfeesinfobutton.setOnClickListener {
            if (tooltipPopup?.isShowing == true) {
                tooltipPopup?.dismiss()
            } else {
                showTooltip(it, "transaction fess")
            }
        }

        binding.taxinfobutton.setOnClickListener {
            if (tooltipPopup?.isShowing == true) {
                tooltipPopup?.dismiss()
            } else {
                showTooltip(it, "taxes fess 2.00")
            }
        }
    }

    private fun showTooltip(anchorView: View, text: String) {
        val tooltipView = LayoutInflater.from(this).inflate(R.layout.tooltip_layout, null)
        val textView: TextView = tooltipView.findViewById(R.id.tooltip_text)
        textView.text = text
        tooltipPopup = PopupWindow(
            tooltipView,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            true
        )

        tooltipPopup?.apply {
            showAsDropDown(anchorView, 0, -anchorView.height)
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
    }
}