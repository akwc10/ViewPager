package com.my

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.my.viewpager.R

// https://developer.android.com/guide/navigation/navigation-swipe-view-2
// https://developer.android.com/training/animation/vp2-migration
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}