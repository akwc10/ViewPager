package com.my.behaviors

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.my.options.OptionsFragment
import com.my.viewpager.R

class BackToOptionsButtonBehavior(fragment: Fragment) {
    init {
        with(fragment) {
            requireActivity().onBackPressedDispatcher.addCallback(this) {
                parentFragmentManager.commit {
                    replace(R.id.main_container, OptionsFragment())
                    setReorderingAllowed(true)
                }
            }
        }
    }
}