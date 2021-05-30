package com.kikuma.kikumaapp.ui.signin

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kikuma.kikumaapp.MainActivity
import com.kikuma.kikumaapp.databinding.ActivitySignInBinding
import com.kikuma.kikumaapp.ui.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var activitySignInBinding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySignInBinding = ActivitySignInBinding.inflate(layoutInflater)
        // Initialize Firebase Auth
        auth = Firebase.auth
        setContentView(activitySignInBinding.root)

        activitySignInBinding.btnSignin.setOnClickListener {
            startSignIn()
        }

        activitySignInBinding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }

    private fun startSignIn() {
        hideKeyboard(activitySignInBinding.root)
        activitySignInBinding.loading.layoutLoading.visibility = View.VISIBLE

        val emailField = activitySignInBinding.edEmail.text.toString()
        val passwordField = activitySignInBinding.edPassword.text.toString()

        auth.signInWithEmailAndPassword(emailField, passwordField)
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
                    val reason = it.exception?.message
                    Toast.makeText(this, "Authentication failed. $reason",
                            Toast.LENGTH_SHORT).show()
                    activitySignInBinding.loading.layoutLoading.visibility = View.GONE
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun hideKeyboard(view: View) {
        view.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    companion object {
        private const val TAG = "CustomAuthActivity"
    }

}