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
                title = "Get to know more",
                description = "Early detection is one of the preventive measures for the skin symptoms to avoid them from getting worse",
                imageDrawable = R.drawable.intro_1,
                backgroundColor = Color.WHITE,
                titleColor = Color.BLACK, descriptionColor = Color.BLACK
        ))
        addSlide(AppIntroFragment.newInstance(
                title = "Kikuma",
                description = "provide the latest condition regarding the state of your facial skin based on the percentage of the type of  acne disease",
                imageDrawable = R.drawable.intro_2,
                backgroundColor = Color.WHITE,
                titleColor = Color.BLACK, descriptionColor = Color.BLACK
        ))
        addSlide(AppIntroFragment.newInstance(
                title = "Let's get started!",
                description = "Presents daily articles about tips for treating and preventing face skin diseases",
                imageDrawable = R.drawable.intro_3,
                backgroundColor = Color.WHITE,
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