package com.my.viewpager.one

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.my.masterdetailfragments.ParentFragment

class ViewPagerOneAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = PAGE_COUNT

    override fun getItem(position: Int): Fragment = ParentFragment.newInstance(position + 1)

    override fun getPageTitle(position: Int): CharSequence = "TAB ${position + 1}"

    companion object {
        private const val PAGE_COUNT = 3
    }
}