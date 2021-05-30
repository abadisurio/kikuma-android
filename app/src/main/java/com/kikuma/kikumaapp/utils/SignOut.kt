package com.kikuma.kikumaapp.utils

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kikuma.kikumaapp.ui.splashscreen.SplashScreenActivity
import java.io.File

class SignOut(private val activity: Activity) {
    fun doSignOut(){
        Firebase.auth.signOut()
        activity?.finish()
        val dir = File(activity.applicationInfo.dataDir + "/databases")
        Log.d("hmm", dir.toString())
        File(dir, "Kikuma.db").delete()
        val intent = Intent(activity, SplashScreenActivity::class.java)
        activity?.startActivity(intent)

    }
}