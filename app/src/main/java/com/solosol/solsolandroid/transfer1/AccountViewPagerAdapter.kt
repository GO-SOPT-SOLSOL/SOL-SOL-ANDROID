package com.solosol.solsolandroid.transfer1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.solosol.solsolandroid.transfer1.custom.CustomTabFragment

private const val NUM_TABS = 3

class AccountViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CustomTabFragment()
            1 -> FriendGroupTabFragment()
            else -> ContactTabFragment()
        }
    }
}