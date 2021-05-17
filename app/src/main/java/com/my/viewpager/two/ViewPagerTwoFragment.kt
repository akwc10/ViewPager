package com.my.viewpager.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.my.viewpager.R

class ViewPagerTwoFragment : Fragment() {
    private lateinit var viewPagerTwoAdapter: ViewPagerTwoAdapter
    private lateinit var viewPager2: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.view_pager_two_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "View Pager 2"
        viewPagerTwoAdapter = ViewPagerTwoAdapter(this)
        viewPager2 = view.findViewById(R.id.pager_2)
        viewPager2.adapter = viewPagerTwoAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_2)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = "TAB ${position + 1}"
        }.attach()
    }
}