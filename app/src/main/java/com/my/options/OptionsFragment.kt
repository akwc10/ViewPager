package com.my.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.my.noviewpager.NoViewPagerFragment
import com.my.viewpager.R
import com.my.viewpager.one.ViewPagerOneFragment
import com.my.viewpager.two.ViewPagerTwoFragment

class OptionsFragment : Fragment() {
    private val view_pager_one_button by lazy { requireActivity().findViewById<Button>(R.id.view_pager_one_button) }
    private val view_pager_two_button by lazy { requireActivity().findViewById<Button>(R.id.view_pager_two_button) }
    private val no_view_pager_button by lazy { requireActivity().findViewById<Button>(R.id.no_view_pager_button) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.options_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Please Select"
        view_pager_one_button.setOnClickListener { showFragment(ViewPagerOneFragment()) }
        view_pager_two_button.setOnClickListener { showFragment(ViewPagerTwoFragment()) }
        no_view_pager_button.setOnClickListener { showFragment(NoViewPagerFragment()) }
    }

    private fun showFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.main_container, fragment)
            setReorderingAllowed(true)
        }
    }

    override fun onDestroyView() {
        listOf(view_pager_one_button, view_pager_two_button, no_view_pager_button)
            .forEach { it.setOnClickListener(null) }
        super.onDestroyView()
    }
}