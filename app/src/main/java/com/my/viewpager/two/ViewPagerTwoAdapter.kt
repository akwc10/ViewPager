package com.my.viewpager.two

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.my.masterdetailfragments.ParentFragment

class ViewPagerTwoAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = PAGE_COUNT

    override fun createFragment(position: Int): Fragment = ParentFragment.newInstance(position + 1)

    companion object {
        private const val PAGE_COUNT = 3
    }
}