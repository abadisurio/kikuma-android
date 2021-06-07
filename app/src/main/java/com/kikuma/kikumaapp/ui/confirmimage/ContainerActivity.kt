package com.kikuma.kikumaapp.ui.confirmimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.ui.result.list.ListResultFragment

class ContainerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HISTORY_ID = "extra_history_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Result"

        val mFragmentManager = supportFragmentManager
        val mContainerFragment = ListResultFragment()
        val fragment = mFragmentManager.findFragmentByTag(ListResultFragment::class.java.simpleName)
        if (fragment !is ListResultFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + ListResultFragment::class.java.simpleName)
            val extras = intent.extras
            if(extras != null){
                val historyId = extras.getString(EXTRA_HISTORY_ID)
                if(historyId!=null){
                    Log.d("cek historyId", historyId)
                    val mBundle = Bundle()
                    mBundle.putString(ListResultFragment.EXTRA_HISTORY_ID, historyId)
                    mContainerFragment.arguments = mBundle

                    mFragmentManager
                            .beginTransaction()
                            .add(R.id.frame_container, mContainerFragment, ListResultFragment::class.java.simpleName)
                            .commit()
                }
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}