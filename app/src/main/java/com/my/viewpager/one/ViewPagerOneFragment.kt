package com.my.viewpager.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.my.viewpager.R

class ViewPagerOneFragment : Fragment() {
    private lateinit var viewPagerOneAdapter: ViewPagerOneAdapter
    private lateinit var viewPager1: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.view_pager_one_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "View Pager 1"
        viewPagerOneAdapter = ViewPagerOneAdapter(childFragmentManager)
        viewPager1 = view.findViewById(R.id.pager_1)
        viewPager1.adapter = viewPagerOneAdapter

        val tabLayout1 = view.findViewById<TabLayout>(R.id.tab_layout_1)
        tabLayout1.setupWithViewPager(viewPager1)
    }
}