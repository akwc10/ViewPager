package com.my.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.my.viewpager.R

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
        requireActivity().title = getString(R.string.please_select_an_option_title)
        view_pager_one_button.setOnClickListener { showFragment(OptionsFragmentDirections.actionOptionsFragmentToViewPagerOneFragment()) }
        view_pager_two_button.setOnClickListener { showFragment(OptionsFragmentDirections.actionOptionsFragmentToViewPagerTwoFragment()) }
        no_view_pager_button.setOnClickListener { showFragment(OptionsFragmentDirections.actionOptionsFragmentToNoViewPagerFragment()) }
    }

    private fun showFragment(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    override fun onDestroyView() {
        listOf(view_pager_one_button, view_pager_two_button, no_view_pager_button)
            .forEach { it.setOnClickListener(null) }
        super.onDestroyView()
    }
}