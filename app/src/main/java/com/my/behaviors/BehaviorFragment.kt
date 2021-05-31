package com.my.behaviors

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BehaviorFragment : Fragment() {
    private var backToOptionsButtonBehavior: BackToOptionsButtonBehavior? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        backToOptionsButtonBehavior = BackToOptionsButtonBehavior(this)
    }

    override fun onDestroy() {
        backToOptionsButtonBehavior = null
        super.onDestroy()
    }
}