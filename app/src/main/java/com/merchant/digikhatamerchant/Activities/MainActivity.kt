package com.merchant.digikhatamerchant.Activities

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.digikhata.merchant.Base.BaseActivity

import com.merchant.digikhatamerchant.Fragment.HomeFragment
import com.merchant.digikhatamerchant.Fragment.AccountFragment
import com.merchant.digikhatamerchant.Fragment.MyQrFragment
import com.merchant.digikhatamerchant.Fragment.SelltlementFragment
import com.merchant.digikhatamerchant.Fragment.TransactionFragment
import com.merchant.digikhatamerchant.R
import com.merchant.digikhatamerchant.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var fragmentManager: FragmentManager
    //  private lateinit var binding: ActivityMainBinding

    override fun setUpViews() {
        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    openFragment(SelltlementFragment())
                    binding.bloatingactionbutton.setImageTintList(
                        ColorStateList.valueOf(
                            ContextCompat.getColor(this, R.color.black)
                        )
                    );
                    openFragment(HomeFragment())
                }

                R.id.QRcode -> {
                    openFragment(SelltlementFragment())
                    binding.bloatingactionbutton.setImageTintList(
                        ColorStateList.valueOf(
                            ContextCompat.getColor(this, R.color.black)
                        )
                    );
                    openFragment(MyQrFragment())
                }

                R.id.settelment -> {
                    openFragment(SelltlementFragment())
                    binding.bloatingactionbutton.setImageTintList(
                        ColorStateList.valueOf(
                            ContextCompat.getColor(this, R.color.black)
                        )
                    );
                }

                R.id.Accounts -> {
                    openFragment(AccountFragment())
                    binding.bloatingactionbutton.setImageTintList(
                        ColorStateList.valueOf(
                            ContextCompat.getColor(this, R.color.black)
                        )
                    );
                }
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

    }

    override fun setUpListeners() {

        binding.bloatingactionbutton.setOnClickListener {
            binding.bottomNavigation.selectedItemId = R.id.Transaction
            binding.bloatingactionbutton.setImageTintList(
                ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.white)
                )
            );
            openFragment(TransactionFragment())
        }
        binding.toolBar.ivNotification.setOnClickListener {
            launch(NotificationActivity::class.java)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.Drawerlayout.isDrawerOpen(GravityCompat.START)) {
            binding.Drawerlayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}