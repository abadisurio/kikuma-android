package com.kikuma.kikumaapp.ui.confirmimage

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.kikuma.kikumaapp.databinding.ActivityConfirmImageBinding
import com.kikuma.kikumaapp.ui.result.list.ListResultFragment


class ConfirmImageActivity : AppCompatActivity() {
    lateinit var activityConfirmImageBinding: ActivityConfirmImageBinding

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image"
//        const val EXTRA_TYPE = "extra_type"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        activityConfirmImageBinding = ActivityConfirmImageBinding.inflate(layoutInflater)
        setContentView(activityConfirmImageBinding.root)
        val extras = intent.extras
        Log.d("extraswkwk", extras.toString())
        if(extras != null){
            val imageView = activityConfirmImageBinding.imageView
            val imageUri = extras.getString(EXTRA_IMAGE_URI)
            Log.d("wkwk", imageUri.toString())
            if(imageUri != null){
                imageView.setImageURI(Uri.parse(imageUri))
            }
        }

        activityConfirmImageBinding.btnNext.setOnClickListener{
            val intent = Intent(this, ContainerActivity::class.java)
            startActivity(intent)
            /*
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.content, ListResultFragment())
            transaction.commit()

             */
        }
    }
    override fun onSupportNavigateUp(): Boolean {
//        startActivity(Intent(this, MainActivity::class.java))
        onBackPressed()
        return true
    }
}