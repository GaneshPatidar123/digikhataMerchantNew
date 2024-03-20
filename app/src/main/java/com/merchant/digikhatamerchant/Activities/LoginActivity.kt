package com.merchant.digikhatamerchant.Activities

import android.app.Dialog
import android.os.Build
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.digikhata.merchant.Base.BaseActivity
import com.example.paypointretailer.Utils.AppUtils
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.ActivityLoginBinding
import com.merchant.digikhatamerchant.databinding.ForgotPasswordLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun setUpViews() {
        super.setUpViews()
    }

    override fun setUpListeners() {


        binding.ivHide.setOnClickListener {
            if (binding.edtPassword.getInputType() === InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                binding.edtPassword.setInputType(
                    InputType.TYPE_CLASS_TEXT or
                            InputType.TYPE_TEXT_VARIATION_PASSWORD
                )
                binding.ivHide.setImageDrawable(resources.getDrawable(R.drawable.ic_show))
            } else {
                binding.ivHide.setImageDrawable(resources.getDrawable(R.drawable.ic_hide))
                binding.edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
            }
            binding.edtPassword.setSelection(binding.edtPassword.text!!.length)
        }


        binding.btnLogin.setOnClickListener {


            if (binding.edtMobileNUmber.text.isNullOrEmpty()) {
                binding.tvErrorMobileNumber.visibility = View.VISIBLE
            }/* else{
                 if(binding.edtMobileNUmber.text?.trim().toString().length != 10){
                     binding.tvErrorMobileNumber.visibility=View.VISIBLE
                     binding.tvErrorMobileNumber.text = getString(R.string.please_enter_valid_mobile_number)
                 }else{
                     binding.tvErrorMobileNumber.visibility=View.GONE
                 }
            }*/
            if (binding.edtPassword.text.isNullOrEmpty()) {
                binding.tvErrorPassword.visibility = View.VISIBLE
            } else {
                binding.tvErrorPassword.visibility = View.GONE
            }
            if (!binding.edtMobileNUmber.text?.trim()
                    .isNullOrEmpty() && !binding.edtPassword.text?.trim().isNullOrEmpty()
            ) {
                /* if (AppUtils.hasInternet(this)) {
                     val request: LoginRequest = LoginRequest(
                         mobile = binding.edtMobileNUmber.text.toString(),
                         password = EncryptionUtils.encrypt(binding.edtPassword.text.toString()),
                         device_token = null,
                         device_type = null,
                         loginfrom = "MobileApp-" + StaticData.currentVersion,
                         device_model = null,
                         uu_id = null,
                         latitude = pref.latitude,
                         longitude = pref.longitude,
                         platform = "Android-" + Build.VERSION.SDK_INT,
                         City =city,
                     )
                     loginViewModel.setLoginEvent(GetLoginDataStateEvent.LoginEvent, request)
                 } else {
                     showErrorMessage(
                         this,
                         getString(R.string.please_check_internet_connection)
                     )
                 }*/
            }
        }

        binding.tvForgot.setOnClickListener {
            showForgotDialog()
        }

        binding.edtPassword.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.llPassword.background =
                    resources.getDrawable(R.drawable.edittext_active_shape)
            } else {
                binding.llPassword.background =
                    resources.getDrawable(R.drawable.edittext_unactive_shape)
            }
        })
    }

    private fun showForgotDialog() {
        val dialog = Dialog(this, R.style.RoundedCornersDialog)
        val mBottomSheetBinding = ForgotPasswordLayoutBinding.inflate(layoutInflater, null, false)
        dialog.setContentView(mBottomSheetBinding.root)
        val window: Window? = dialog.window
        window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        dialog.setCancelable(false)
        mBottomSheetBinding.tvCamcel.setOnClickListener {
            dialog.dismiss()
        }
        mBottomSheetBinding.tvContinues.setOnClickListener {
            if (mBottomSheetBinding.edtMobileNUmber.text.toString().isNullOrEmpty()) {
                mBottomSheetBinding.tvErrorMesg.visibility = View.VISIBLE
                mBottomSheetBinding.tvErrorMesg.text =
                    getString(R.string.please_enter_mobile_number)
            } else if (mBottomSheetBinding.edtMobileNUmber.text!!.length < 10) {
                mBottomSheetBinding.tvErrorMesg.visibility = View.VISIBLE
                mBottomSheetBinding.tvErrorMesg.text =
                    getString(R.string.please_enter_valid_mobile_number)
            } else {
                mBottomSheetBinding.tvErrorMesg.visibility = View.GONE
                dialog.dismiss()
                /*   isforgotClick = true;
                   val request: RequestCheckMobileDeviceUsed =
                       RequestCheckMobileDeviceUsed(
                           Cordova = null,
                           IsVirtual = null,
                           Manufacturer = Build.MANUFACTURER,
                           Model = Build.MODEL,
                           Platform = "Android",
                           Serial = Build.SERIAL,
                           Uuid = AppUtils.getUUID(sharedPrefs),
                           Version = Build.VERSION.SDK_INT.toString(),
                           key = null,
                           PushNotificationRegID = null,
                           Mobile = mBottomSheetBinding.edtMobileNUmber.text.toString()
                       )
                   loginViewModel.apiCallForgotPassword(
                       GetLoginDataStateEvent.CheckDeviceUsedEvent,
                       request
                   )*/
            }

        }
        dialog.show()
    }

    override fun setUpObservers() {
        super.setUpObservers()
    }
}