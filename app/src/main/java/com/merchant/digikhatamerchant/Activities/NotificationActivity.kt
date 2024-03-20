package com.merchant.digikhatamerchant.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digikhata.merchant.Base.BaseActivity
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.ActivityNotificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationActivity :  BaseActivity<ActivityNotificationBinding>(R.layout.activity_notification) {
    override fun setUpViews() {
         binding.toolBar.tvTitle.text = getString(R.string.notification)
    }

    override fun setUpListeners() {

    }

    override fun setUpObservers() {

    }
}