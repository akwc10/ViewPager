package com.my

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.my.viewpager.R

// https://developer.android.com/guide/navigation/navigation-swipe-view-2
// https://developer.android.com/training/animation/vp2-migration
class MainActivity : AppCompatActivity() {
    private val navController by lazy { (supportFragmentManager.findFragmentById(R.id.main_content) as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.tool_bar))
        supportActionBar?.subtitle = "Subtitle"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
//        TODO Requires child activity - see https://developer.android.com/training/appbar/up-action
        return navController.navigateUp()
//        return super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> {
            Toast.makeText(this.applicationContext, "Settings", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.action_favorite -> {
            Toast.makeText(this.applicationContext, "Favourite", Toast.LENGTH_SHORT).show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}