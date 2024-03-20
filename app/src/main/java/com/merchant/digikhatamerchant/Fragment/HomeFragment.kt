package com.merchant.digikhatamerchant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digikhata.merchant.Base.BaseFragment
import com.example.paypointretailer.Utils.AppUtils
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    var selectedDate = ""
    override fun setUpViews() {
        super.setUpViews()
    }

    override fun setUpListeners() {
        binding.tvMonth.setOnClickListener {
            binding.tvMonth.setTextColor(requireActivity().resources.getColor(R.color.white))
            binding.tvMonth.setBackgroundDrawable(resources.getDrawable(R.drawable.background_dark_blue_shape))

            binding.tvSevenDay.setTextColor(requireActivity().resources.getColor(R.color.black_ppi))
            binding.tvSevenDay.setBackgroundDrawable(resources.getDrawable(R.drawable.edittext_active_shape))

            binding.tvOneDay.setTextColor(requireActivity().resources.getColor(R.color.black_ppi))
            binding.tvOneDay.setBackgroundDrawable(resources.getDrawable(R.drawable.edittext_active_shape))
            selectedDate = AppUtils.beforeOneMonths()
        }
        binding.tvSevenDay.setOnClickListener {
            binding.tvSevenDay.setTextColor(requireActivity().resources.getColor(R.color.white))
            binding.tvSevenDay.setBackgroundDrawable(resources.getDrawable(R.drawable.background_dark_blue_shape))

            binding.tvMonth.setTextColor(requireActivity().resources.getColor(R.color.black_ppi))
            binding.tvMonth.setBackgroundDrawable(resources.getDrawable(R.drawable.edittext_active_shape))

            binding.tvOneDay.setTextColor(requireActivity().resources.getColor(R.color.black_ppi))
            binding.tvOneDay.setBackgroundDrawable(resources.getDrawable(R.drawable.edittext_active_shape))
            selectedDate = AppUtils.beforeSevenDays()
        }

        binding.tvOneDay.setOnClickListener {
            binding.tvOneDay.setTextColor(requireActivity().resources.getColor(R.color.white))
            binding.tvOneDay.setBackgroundDrawable(resources.getDrawable(R.drawable.background_dark_blue_shape))

            binding.tvMonth.setTextColor(requireActivity().resources.getColor(R.color.black_ppi))
            binding.tvMonth.setBackgroundDrawable(resources.getDrawable(R.drawable.edittext_active_shape))

            binding.tvSevenDay.setTextColor(requireActivity().resources.getColor(R.color.black_ppi))
            binding.tvSevenDay.setBackgroundDrawable(resources.getDrawable(R.drawable.edittext_active_shape))
            selectedDate = AppUtils.getCurrentDate()
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
    }
}