package com.merchant.digikhatamerchant.Activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import androidx.annotation.RequiresApi
import com.digikhata.merchant.Base.BaseActivity
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.ActivityWebViewBinding

class WebViewActivity : BaseActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {
    override fun setUpViews() {
        getDataFromIntents(intent)
    }


    private fun getDataFromIntents(intent: Intent?) {
        val bundle = intent?.extras
        /*if (bundle != null) {
            var url = bundle.getString(AppConstant.IS_FROM)!!
            var isClick = bundle.getString("isClick")
            if (isClick.equals(EnumData.TRAIN.text.toString()) || isClick.equals(EnumData.JIO_SAVAN.text.toString()) || isClick.equals(EnumData.PAYPOINT_CARD.text.toString()) ||
                isClick.equals(EnumData.DIGIGOLD_GOLD.text.toString()) || isClick.equals(EnumData.SAVING_PLAN_SIP.text.toString())
            ) {
                val pgURL: String = url.split('?')[0]
                val pgToken: String = url.split('?')[1]
                val pgContent = """
   <html><head></head><body><form id="PostForm" name="PostForm" action="$pgURL" method="POST"><input type="hidden" name="Token" value="$pgToken"></form></body></html><script language='javascript'>var vPostForm = document.PostForm;vPostForm.submit();</script>
""".trimIndent()
                try {
                    // binding.webView.webViewClient = WebViewClient()
                    binding.webView.settings.mixedContentMode =
                        WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    binding.webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                    val base64EncodedHtmlString = encodeToBase64(pgContent)
                    try {
                        val decodedBytes = Base64.decode(base64EncodedHtmlString, Base64.DEFAULT)
                        val decodedHtmlString = String(decodedBytes, Charsets.UTF_8)
                        binding.webView.settings.javaScriptEnabled = true
                        //  binding.webView.webViewClient = WebViewClient()
                        binding.webView.webViewClient = MyWebViewClient()
                        binding.webView.loadData(
                            "data:text/html;base64," + decodedHtmlString,
                            "text/html",
                            "UTF-8"
                        )
                    } catch (e: IllegalArgumentException) {
                        e.printStackTrace()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                binding.webView.settings.javaScriptEnabled = true
                binding.webView.webViewClient = MyWebViewClient()
                binding.webView.webChromeClient = WebChromeClient()
                binding.webView.loadUrl(url)

            }


        }
*/

    }

    override fun setUpListeners() {
        super.setUpListeners()
    }
}