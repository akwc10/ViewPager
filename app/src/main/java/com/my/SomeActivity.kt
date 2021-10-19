package com.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.viewpager.R

class SomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ViewPager)
        setContentView(R.layout.activity_some)
    }
}