package com.solosol.solsolandroid.transfer1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.setMargins
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.solosol.solsolandroid.R
import com.solosol.solsolandroid.databinding.ActivityTransfer1Binding
import kotlinx.coroutines.launch
import java.lang.Exception

class Transfer1Activity : AppCompatActivity() {

    private val binding by lazy { ActivityTransfer1Binding.inflate(layoutInflater) }

    private val tabTitleArray = arrayOf(
        "맞춤",
        "친구/그룹",
        "연락처"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.vpAccountList.adapter = AccountViewPagerAdapter(this)

        TabLayoutMediator(binding.tlAccount, binding.vpAccountList) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
        setTabItemMargin(binding.tlAccount)

        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch {

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