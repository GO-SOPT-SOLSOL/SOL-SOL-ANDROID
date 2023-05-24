package com.solosol.solsolandroid.transfer1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val NUM_TABS = 3

class AccountViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

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