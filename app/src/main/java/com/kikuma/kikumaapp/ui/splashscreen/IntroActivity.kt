package com.kikuma.kikumaapp.ui.splashscreen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.ui.signin.SignInActivity

class IntroActivity :  AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!

        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(AppIntroFragment.newInstance(
                title = "Welcome...",
                description = "This is the first slide of the example",
                imageDrawable = R.drawable.loading,
                titleColor = Color.BLACK, descriptionColor = Color.BLACK
        ))
        addSlide(AppIntroFragment.newInstance(
                title = "...Let's get started!",
                description = "This is the last slide, I won't annoy you more :)",
                imageDrawable = R.drawable.loading,
                titleColor = Color.BLACK, descriptionColor = Color.BLACK
        ))
        addSlide(AppIntroFragment.newInstance(
                title = "...Let's get started!",
                description = "This is the last slide, I won't annoy you more :)",
                imageDrawable = R.drawable.loading,
                titleColor = Color.BLACK, descriptionColor = Color.BLACK
        ))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        goToNextSlide()
        goToNextSlide()
//        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
}