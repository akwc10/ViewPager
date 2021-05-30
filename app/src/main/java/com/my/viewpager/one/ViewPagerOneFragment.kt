package com.my.viewpager.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.my.behaviors.BehaviorFragment
import com.my.viewpager.R

class ViewPagerOneFragment : BehaviorFragment() {
    private lateinit var viewPagerOneAdapter: ViewPagerOneAdapter
    private lateinit var viewPagerOne: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.view_pager_one_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "View Pager One"
        viewPagerOneAdapter = ViewPagerOneAdapter(childFragmentManager)
        viewPagerOne = view.findViewById(R.id.view_pager_one)
        viewPagerOne.adapter = viewPagerOneAdapter

        val tabLayout1 = view.findViewById<TabLayout>(R.id.tab_layout_one)
        tabLayout1.setupWithViewPager(viewPagerOne)
    }
}