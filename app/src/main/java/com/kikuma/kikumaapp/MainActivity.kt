package com.kikuma.kikumaapp

import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kikuma.kikumaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val navView: BottomNavigationView = activityMainBinding.navView
        setContentView(activityMainBinding.root)

        supportActionBar?.hide()

//        val viewTreeObserver: ViewTreeObserver = navView.viewTreeObserver
//        if(viewTreeObserver.isAlive){
//            viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
//                override fun onGlobalLayout() {
//                    val viewHeight: Int = navView.height
//                    if (viewHeight != 0) navView.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
//                    navHostFragment?.view?.marginBottom = viewHeight
//                }
//            })
//        }

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_camera, R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}