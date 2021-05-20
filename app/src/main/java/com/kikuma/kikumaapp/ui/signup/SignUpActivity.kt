package com.kikuma.kikumaapp.ui.signup

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kikuma.kikumaapp.MainActivity
import com.kikuma.kikumaapp.databinding.ActivitySignInBinding
import com.kikuma.kikumaapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var activitySignUpBinding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySignUpBinding = ActivitySignUpBinding.inflate(layoutInflater)

        activitySignUpBinding.btnSignup.setOnClickListener {
            startSignUp()
        }

        auth = Firebase.auth
        setContentView(activitySignUpBinding.root)

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }

    private fun startSignUp() {

        val emailField = activitySignUpBinding.edEmail.text.toString()
        val passwordField = activitySignUpBinding.edPassword.text.toString()

        auth.createUserWithEmailAndPassword(emailField, passwordField)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user: FirebaseUser? = auth.currentUser
                    updateUI(user)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", it.exception)
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

    }


    companion object {
        private const val TAG = "CustomAuthActivity"
    }

}