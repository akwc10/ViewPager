package com.my.viewpager.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.my.behaviors.BehaviorFragment
import com.my.viewpager.R

class ViewPagerTwoFragment : BehaviorFragment() {
    private lateinit var viewPagerTwoAdapter: ViewPagerTwoAdapter
    private lateinit var viewPagerTwo: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.view_pager_two_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.view_pager_two_title)
        viewPagerTwoAdapter = ViewPagerTwoAdapter(this)
        viewPagerTwo = view.findViewById(R.id.view_pager_two)
        viewPagerTwo.adapter = viewPagerTwoAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_two)
        TabLayoutMediator(tabLayout, viewPagerTwo) { tab, position ->
            tab.text = "TAB ${position + 1}"
        }.attach()
    }
}