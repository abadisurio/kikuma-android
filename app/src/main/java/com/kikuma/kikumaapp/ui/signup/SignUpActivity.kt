package com.kikuma.kikumaapp.ui.signup

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
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

        activitySignUpBinding.btnSignin.setOnClickListener {
            onBackPressed()
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

        val emailField = activitySignUpBinding.edEmail
        val passwordField = activitySignUpBinding.edPassword
        val nameField = activitySignUpBinding.edName

        if(
            TextUtils.isEmpty(emailField.text) ||
            TextUtils.isEmpty(passwordField.text) ||
            TextUtils.isEmpty(nameField.text)
        ){
            emailField.error = "Required!" 
            passwordField.error = "Required!"
            nameField.error = "Required!"
        }else{
            hideKeyboard(activitySignUpBinding.root)
            activitySignUpBinding.loading.layoutLoading.visibility = View.VISIBLE
            auth.createUserWithEmailAndPassword(emailField.text.toString(), passwordField.text.toString())
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user: FirebaseUser? = auth.currentUser
                        updateUI(user)

                        val profileUpdate = UserProfileChangeRequest
                                .Builder()
                                .setDisplayName(nameField.text.toString())
                                .build()
                        user?.updateProfile(profileUpdate)?.addOnCompleteListener {updateName ->
                            if(updateName.isSuccessful){
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                        }

                    }else{
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", it.exception)
                        val reason = it.exception?.message
                        Toast.makeText(this, "Authentication failed. $reason",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                        activitySignUpBinding.loading.layoutLoading.visibility = View.GONE
                    }
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