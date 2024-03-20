package com.merchant.digikhatamerchant.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digikhata.merchant.Base.BaseActivity
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.ActivityLoginBinding
import com.merchant.digikhatamerchant.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    override fun setUpViews() {
        binding.toolBar.tvTitle.text = getString(R.string.profile)
    }

    override fun setUpListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.toolBar.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
    }
}