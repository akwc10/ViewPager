package com.my.viewpager.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.my.behaviors.BackToOptionsButtonBehavior
import com.my.viewpager.R

class ViewPagerOneFragment : Fragment() {
    private lateinit var viewPagerOneAdapter: ViewPagerOneAdapter
    private lateinit var viewPagerOne: ViewPager
    private var backToOptionsButtonBehavior: BackToOptionsButtonBehavior? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backToOptionsButtonBehavior = BackToOptionsButtonBehavior(this)
    }

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

    override fun onDestroy() {
        backToOptionsButtonBehavior = null
        super.onDestroy()
    }
}