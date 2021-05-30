package com.kikuma.kikumaapp.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kikuma.kikumaapp.MainActivity
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.databinding.ActivitySplashScreenBinding
import com.kikuma.kikumaapp.ui.signin.SignInActivity
import com.kikuma.kikumaapp.ui.signup.SignUpActivity

class SplashScreenActivity : AppCompatActivity() {

    private val splashTimeOut:Long = 500 // 1 sec
    private lateinit var auth: FirebaseAuth
    private lateinit var authUID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        setContentView(R.layout.activity_splash_screen)
//        auth.signOut()
        authUID = auth.currentUser?.uid.toString()
//        Log.d("wkwk", authUID)

        Handler(mainLooper).postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            if (authUID != "null"){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            }

            // close this activity
        }, splashTimeOut)
//
    }
}