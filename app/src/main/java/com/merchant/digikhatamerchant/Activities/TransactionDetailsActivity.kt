package com.merchant.digikhatamerchant.Activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.FileProvider
import com.digikhata.merchant.Base.BaseActivity
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.ActivityTransactionDetailsBinding
import com.merchant.digikhatamerchant.databinding.DialogRefundLayoutBinding
import com.merchant.digikhatamerchant.databinding.DialogSettlementLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class TransactionDetailsActivity :BaseActivity<ActivityTransactionDetailsBinding>(R.layout.activity_transaction_details) {
    private lateinit var mBottomSheetBinding: DialogRefundLayoutBinding
    override fun setUpViews() {
        binding.toolBar.tvTitle.text = getString(R.string.transaction_Details)
    }

    override fun setUpListeners() {
        binding.toolBar.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnRefund.setOnClickListener {
            showRefundDialog()
        }
        binding.btnShare.setOnClickListener {
            val rootView = window.decorView.findViewById<View>(android.R.id.content)
            val file = captureScreen(rootView, this)
            file?.let {
                shareImage(it, this)
            }
        }
    }

    fun captureScreen(view: View, activity: Activity): File? {
        // Create a Bitmap with the same dimensions as the View
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        // Draw the view onto the Bitmap
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        // Try to save the file
        return try {
            // Create a file in the cache directory
            val file = File(activity.cacheDir, "screenshot.png")
            file.outputStream().use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
                out.flush()
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun shareImage(file: File, activity: Activity) {
        val uri: Uri = FileProvider.getUriForFile(activity, "${activity.packageName}.provider", file)

        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/png"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        activity.startActivity(Intent.createChooser(shareIntent, "Share Screenshot"))
    }

    private fun showRefundDialog() {
        val dialog = Dialog(this, R.style.RoundedCornersDialog)
        mBottomSheetBinding =
            DialogRefundLayoutBinding.inflate(layoutInflater, null, false)
        dialog.setContentView(mBottomSheetBinding.root)
        val window: Window? = dialog.window
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        dialog.setCancelable(false)

        mBottomSheetBinding.btnsubmit.setOnClickListener {
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
}