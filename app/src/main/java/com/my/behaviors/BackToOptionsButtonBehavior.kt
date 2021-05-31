package com.my.behaviors

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.my.noviewpager.NoViewPagerFragment
import com.my.noviewpager.NoViewPagerFragmentDirections
import com.my.viewpager.R
import com.my.viewpager.one.ViewPagerOneFragment
import com.my.viewpager.one.ViewPagerOneFragmentDirections
import com.my.viewpager.two.ViewPagerTwoFragment
import com.my.viewpager.two.ViewPagerTwoFragmentDirections

class BackToOptionsButtonBehavior(fragment: Fragment) {
    init {
        with(fragment) {
            requireActivity().onBackPressedDispatcher.addCallback(this) {
//                TODO Can't id fragments. Can't use tags due to view pager. Will need to track navigation?!?
                run loop@{
                    listOf(
                        R.id.view_pager_one,
                        R.id.view_pager_two,
                        R.id.no_view_pager
                    ).forEach {
                        childFragmentManager.findFragmentById(it)?.let {
                            findNavController().navigate(
                                when (it) {
                                    is ViewPagerOneFragment -> ViewPagerOneFragmentDirections.actionViewPagerOneFragmentToOptionsFragment()
                                    is ViewPagerTwoFragment -> ViewPagerTwoFragmentDirections.actionViewPagerTwoFragmentToOptionsFragment()
                                    is NoViewPagerFragment -> NoViewPagerFragmentDirections.actionNoViewPagerFragmentToOptionsFragment()
                                    else -> throw IllegalStateException("Fragment unsupported: $it")
                                }
                            )
                            return@loop
                        }
                    }
                }
            }
        }
    }
}