package com.my.viewpager.two

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.my.masterdetailfragments.ParentFragment

class ViewPagerTwoAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        ParentFragment.newInstance(position + 1)
}