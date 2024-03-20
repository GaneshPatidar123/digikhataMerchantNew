package com.merchant.digikhatamerchant.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.digikhata.merchant.Base.BaseActivity
import com.example.paypointretailer.Utils.AppUtils
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.ActivityAddBankBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBankActivity : BaseActivity<ActivityAddBankBinding>(R.layout.activity_add_bank) {
    override fun setUpViews() {
        binding.toolBar.tvTitle.text = getString(R.string.add_bank)
    }

    override fun setUpListeners() {
        binding.btnAdd.setOnClickListener {
            var isvalideIfsc = false
            if (binding.edtSelect.text.isNullOrEmpty()) {
                binding.errorSelectBank.visibility = View.VISIBLE
            } else {
                binding.errorSelectBank.visibility = View.GONE
            }

            if (binding.edtIFSECode.text.isNullOrEmpty()) {
                binding.errorIFSECode.visibility = View.VISIBLE
                binding.errorIFSECode.text = "Please enter IFSC Code"
            } else {
                isvalideIfsc = AppUtils.isValidIFSCCode(binding.edtIFSECode.text.toString())
                if (!isvalideIfsc) {
                    binding.errorIFSECode.visibility = View.VISIBLE
                    binding.errorIFSECode.text = "Please Enter Valid IFSC Code"
                } else {
                    binding.errorIFSECode.visibility = View.GONE
                }

            }

            if (binding.edtSelectAccountType.text.isNullOrEmpty()) {
                binding.errorSelectAccontType.visibility = View.VISIBLE
            } else {
                binding.errorSelectAccontType.visibility = View.GONE
            }


            if (binding.edtAccountNumber.text.isNullOrEmpty()) {
                binding.errorAccountNumber.visibility = View.VISIBLE
                binding.errorAccountNumber.text = "Please enter account Number"
            } else {
                if (binding.edtAccountNumber.text!!.length < 9) {
                    binding.errorAccountNumber.visibility = View.VISIBLE
                    binding.errorAccountNumber.text =
                        "Please enter account Number in Bitween 9 to 18 digit"
                } else {
                    binding.errorAccountNumber.visibility = View.GONE
                }

            }

            if (binding.edtAccountholdername.text.isNullOrEmpty()) {
                binding.errorAccountHolderName.visibility = View.VISIBLE
            } else {
                binding.errorAccountHolderName.visibility = View.GONE
            }

            if (!binding.edtSelect.text.isNullOrEmpty() && !binding.edtIFSECode.text.isNullOrEmpty() && isvalideIfsc &&
                !binding.edtSelectAccountType.text.isNullOrEmpty() && !binding.edtAccountNumber.text.isNullOrEmpty() && binding.edtAccountNumber.text!!.length > 9 && !binding.edtAccountholdername.text.isNullOrEmpty()
            ) {
                /* if (AppUtils.hasInternet(this)) {
                     val request: RequestAddBankDetails = RequestAddBankDetails(
                         authorization = pref.getData()?.access_token,
                         icode = pref.getData()?.IdentificationCode,
                         pcode = EncryptionUtils.encrypt(pref.getData()?.DevicePassword),
                         Key = pref.getData()?.BusinessId.toString(),
                         devicecode = pref.getData()?.DeviceCode,
                         Bank = binding.edtSelect.text.toString(),
                         AccountType = binding.edtSelectAccountType.text.toString(),
                         AccountHolderName = binding.edtAccountholdername.text.toString(),
                         AccountNumber = binding.edtAccountNumber.text.toString(),
                         IFSCCode = binding.edtIFSECode.text.toString(),
                         MobileNo = pref.getData()?.Mobile
                     )
                     // setPositionSix()
                     viewModel.callAddBankDetails(StepperStateEvent.stepFive, request)
                 } else {
                     showErrorMessage(this, getString(R.string.please_check_internet_connection))
                 }*/

            }
        }
        binding.toolBar.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
    }
}