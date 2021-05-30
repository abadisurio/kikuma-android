package com.kikuma.kikumaapp.ui.confirmimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.ui.result.list.ListResultFragment

class ContainerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_HISTORY_ID = "extra_history_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val mFragmentManager = supportFragmentManager
        val mHomeFragment = ListResultFragment()
        val fragment = mFragmentManager.findFragmentByTag(ListResultFragment::class.java.simpleName)
        if (fragment !is ListResultFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + ListResultFragment::class.java.simpleName)
            mFragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, mHomeFragment, ListResultFragment::class.java.simpleName)
                    .commit()
        }
    }
}