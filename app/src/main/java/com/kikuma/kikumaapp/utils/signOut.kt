package com.kikuma.kikumaapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.kikuma.kikumaapp.ui.splashscreen.SplashScreenActivity

class signOut(private val activity: Activity) {
    private lateinit var auth: FirebaseAuth
    fun doSignOut(){auth.signOut()
        activity?.finish()

        val intent = Intent(activity, SplashScreenActivity::class.java)
        activity?.startActivity(intent)

    }
}