package com.digikhata.merchant.Base


import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import com.example.paypointretailer.Utils.AppSharedPref
import com.example.paypointretailer.Utils.AppUtils
import com.merchant.digikhatamerchant.R

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

open class BaseActivity<DB : ViewDataBinding>(private val layoutId: Int) : AppCompatActivity() {

    private lateinit var _binding: DB
    val binding get() = _binding

    @Inject
    lateinit var pref: AppSharedPref

    private var mProgressDialog: Dialog? = null
    var callBack: onDateSelect? = null

    interface onDateSelect {
        fun date(date: String, editText: AppCompatEditText?)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutId != 0) {
            _binding = DataBindingUtil.setContentView(this, layoutId)
            binding.lifecycleOwner = this
        } else {
            throw IllegalArgumentException("Layout resource can't be null")
        }

        setUpViews()
        setUpListeners()
        performApiCall()
        setUpObservers()
        getDataFromIntent()
    }

    fun showToastLong(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showToastShort(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    /* fun noInternetDialog(onTryAgainClick: () -> Unit, tempActivity: Activity) {
         createDialog(tempActivity)
         AppUtils.dialog?.setOnKeyListener { _, keyCode, _ ->
             if (keyCode == KeyEvent.KEYCODE_BACK) {
                 AppUtils.dialog?.dismiss()
                 if (AppUtils.hasInternet(tempActivity)) {
                     onTryAgainClick()
                 } else {
                     tempActivity.finishAffinity()
                 }
             }
             true
         }
         AppUtils.dialog?.findViewById<View>(R.id.btn_try_again)?.setOnClickListener {
             if (AppUtils.hasInternet(tempActivity)) {
                 AppUtils.dialog?.dismiss()
                 onTryAgainClick()
             } else {
                 *//*AppUtils.showToast(
                    tempActivity,
//                    AppUtils.activity.getString(com.helpN.R.string.err_no_internet)
                    "NO Internet"
                )*//*
            }
        }

        if (!AppUtils.dialog!!.isShowing) {
            AppUtils.dialog?.show()
        }

    }

    fun createDialog(context: Context) {
        Log.i("AppInterceptor", "createDialog::::::::::::::::")
        AppUtils.dialog = Dialog(context, R.style.Theme_DeviceDefault_Wallpaper)
        AppUtils.dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.dialog_no_internet, null)
        AppUtils.dialog?.setContentView(view)
        val window = AppUtils.dialog?.window
        val wlp = window?.attributes

        wlp?.gravity = Gravity.CENTER
        window?.attributes = wlp
        AppUtils.dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        AppUtils.dialog?.setCancelable(false)
        if (!AppUtils.dialog!!.isShowing) {
            AppUtils.dialog?.show()
        }


    }*/

    open fun showProgressDialog() {
        mProgressDialog?.let {
            if (!it.isShowing) it.show()
        } ?: run {
            mProgressDialog = Dialog(this@BaseActivity).apply {
                window?.let {
                    it.requestFeature(Window.FEATURE_NO_TITLE)
                    it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    //  it.setDimAmount(0.2f)
                }
                setContentView(R.layout.progress_dialog)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
            }
            if (mProgressDialog?.isShowing == false) mProgressDialog?.show()
        }
    }

    open fun hideProgressDialog() {
        mProgressDialog?.let {
            if (it.isShowing) it.dismiss()
        }
    }

    fun launch(activityToStart: Class<*>) {
        startActivity(Intent(this, activityToStart))
    }

    fun launch(activity: Activity, activityToStart: Class<*>) {
        startActivity(Intent(activity, activityToStart))
    }

    fun launch(activity: Activity, activityToStart: Class<*>, bundle: Bundle) {
        val intent = Intent(activity, activityToStart)
        // intent.putExtra("DATABUNDLE", bundle)
        intent.putExtras(bundle);
        activity.startActivity(intent)
    }

    fun launchNewFirstActivity(activityToStart: Class<*>) {
        val intent = Intent(applicationContext, activityToStart)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()

    }

    fun launchNewFirstActivity(activity: Activity, activityToStart: Class<*>) {
        val intent = Intent(activity, activityToStart)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
        finish()

    }

    fun showDatePicker(call: onDateSelect, edtFromDate: AppCompatEditText) {
        callBack = call
        val calendar = Calendar.getInstance()
        val minDate = Calendar.getInstance()
        minDate.set(2020, Calendar.JANUARY, 1) // Set your minimum date here


        val datePickerDialog = DatePickerDialog(
            AppUtils.activity,
            R.style.datepicker,
            { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                callBack?.date(formattedDate, edtFromDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = minDate.timeInMillis
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
    fun showDatePickerTodate(call: onDateSelect, edtFromDate: AppCompatEditText, fromDate: String) {
        callBack = call
        val calendar = Calendar.getInstance()
        val minDate = Calendar.getInstance()
        minDate.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(fromDate) ?: Date()

        val datePickerDialog = DatePickerDialog(
            AppUtils.activity,
            R.style.datepicker,
            { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                callBack?.date(formattedDate, edtFromDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Set min date to the selected date from showDatePicker
        datePickerDialog.datePicker.minDate = minDate.timeInMillis

        // Set max date to the end of the same month of the selected min date
        val maxDate = Calendar.getInstance()
        maxDate.time = minDate.time
        maxDate.set(Calendar.DAY_OF_MONTH, maxDate.getActualMaximum(Calendar.DATE))
      //  datePickerDialog.datePicker.maxDate = maxDate.timeInMillis
        datePickerDialog.datePicker.maxDate = minOf(maxDate.timeInMillis, Calendar.getInstance().timeInMillis)
        datePickerDialog.show()

    }

    fun showDatePickerjourny(call: onDateSelect, edtFromDate: AppCompatEditText) {
        callBack = call
        val calendar = Calendar.getInstance()
        val minDate = Calendar.getInstance()
        minDate.set(2020, Calendar.JANUARY, 1) // Set your minimum date here


        val datePickerDialog = DatePickerDialog(
            AppUtils.activity,
            R.style.datepicker,
            { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = formatDate(year, monthOfYear, dayOfMonth)
                val formattedDate = dateFormat.format(selectedDate.time)
                callBack?.date(formattedDate, edtFromDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun changeDateFormat(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")

        try {
            val date: Date = inputFormat.parse(inputDate) ?: Date()
            return outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "" // Return an empty string or handle the error as needed
    }



    /*  fun launchNewFirstActivity(activity: Activity, activityToStart: Class<*>, bundle: Bundle) {
          val intent = Intent(activity, activityToStart)
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
          intent.putExtra(AppConstant.BUNDLE, bundle)
          startActivity(intent)
          finish()
      }*/

    open fun setUpViews() {}

    open fun setUpListeners() {}

    open fun performApiCall() {}

    open fun setUpObservers() {}

    open fun getDataFromIntent() {}

}
