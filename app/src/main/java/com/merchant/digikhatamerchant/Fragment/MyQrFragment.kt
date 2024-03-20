package com.merchant.digikhatamerchant.Fragment

import android.content.Context
import com.digikhata.merchant.Base.BaseFragment
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.FragmentMyQrBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL


@AndroidEntryPoint
class MyQrFragment : BaseFragment<FragmentMyQrBinding>(R.layout.fragment_my_qr)  {
    override fun setUpViews() {
       // super.setUpViews()
        downloadImage(requireActivity(),"https://example.com/image.jpg","image.jpg")
    }

    override fun setUpListeners() {
        // download Qr COde image

    }

    override fun setUpObservers() {
        super.setUpObservers()
    }
    fun downloadImage(context: Context, imageUrl: String, imageName: String) {

        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val input = connection.inputStream
                val output: FileOutputStream =
                    FileOutputStream(File(requireActivity().getExternalFilesDir(null), "downloadedImage.jpg"))
                val buffer = ByteArray(4096)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) {
                    output.write(buffer, 0, bytesRead)
                }
                input.close()
                output.close()
            } else {
                println("Request failed with HTTP error code: $responseCode")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}