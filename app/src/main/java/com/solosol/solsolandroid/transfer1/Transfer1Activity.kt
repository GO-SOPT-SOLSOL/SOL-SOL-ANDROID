package com.solosol.solsolandroid.transfer1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.setMargins
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.solosol.solsolandroid.R
import com.solosol.solsolandroid.ServicePool
import com.solosol.solsolandroid.Transfer2Activity
import com.solosol.solsolandroid.databinding.ActivityTransfer1Binding
import kotlinx.coroutines.launch
import java.lang.Exception

class Transfer1Activity : AppCompatActivity() {

    private val binding by lazy { ActivityTransfer1Binding.inflate(layoutInflater) }
    private val lastTransferListAdapter by lazy {
        LastTransferListAdapter {
            Intent(this, Transfer2Activity::class.java).apply {
                putExtra("accountNumber", it.accountNumber)
                putExtra("userName", it.name)
                putExtra("bank", it.bank)
            }.let(::startActivity)
        }
    }
    private val tabTitleArray = arrayOf(
        "맞춤",
        "친구/그룹",
        "연락처"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewPager()
        initTab()
        fetchData()
    }

    private fun initViewPager() {
        with(binding) {
            vpAccountList.adapter = AccountViewPagerAdapter(this@Transfer1Activity)
            rvLastTransferList.apply {
                adapter = lastTransferListAdapter
            }
            ivBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun initTab() {
        TabLayoutMediator(binding.tlAccount, binding.vpAccountList) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
        setTabItemMargin(binding.tlAccount)
    }

    private fun fetchData() {
        lifecycleScope.launch {
            val response = ServicePool.solService.getRecentTransferAccounts(1)
            if (response.isSuccessful) {
                val accountList = response.body()?.data
                lastTransferListAdapter.submitList(accountList?.transfers ?: emptyList())
            }
        }
    }

    private fun setTabItemMargin(tabLayout: TabLayout, marginEnd: Int = 8) {
        try {
            for (i in 0 until 3) {
                val tabs = tabLayout.getChildAt(0) as ViewGroup
                for (i in 0 until tabs.childCount) {
                    val tab = tabs.getChildAt(i)
                    val lp = tab.layoutParams as LinearLayout.LayoutParams
                    lp.setMargins(marginEnd)

                    tab.layoutParams = lp
                    tabLayout.requestLayout()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}