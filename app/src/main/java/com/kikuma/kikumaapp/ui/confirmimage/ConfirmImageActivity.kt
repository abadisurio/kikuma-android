package com.kikuma.kikumaapp.ui.confirmimage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.databinding.ActivityConfirmImageBinding
import com.kikuma.kikumaapp.ui.result.list.ListResultFragment
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory
import java.io.ByteArrayOutputStream


class ConfirmImageActivity : AppCompatActivity() {
    lateinit var activityConfirmImageBinding: ActivityConfirmImageBinding

    lateinit var imageUri: String


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
            imageUri = extras.getString(EXTRA_IMAGE_URI).toString()
            Log.d("wkwk", imageUri)
            imageView.setImageURI(Uri.parse(imageUri))
        }
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[ConfirmImageViewModel::class.java]

        activityConfirmImageBinding.btnNext.setOnClickListener{
//            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.content, ListResultFragment())
//            transaction.commit()

            val imageStream = contentResolver.openInputStream(Uri.parse(imageUri))
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            val encodedImage = encodeImage(selectedImage)
            if(encodedImage!=null){
                viewModel.setImageBase64(encodedImage)
                viewModel.uploadImage()
                viewModel.isSuccess.observe(this, {
                    if(it){
                        val hisId = viewModel.historyId.value.toString()
                        Log.d("wgwg2", it.toString())
                        val intent = Intent(this, ContainerActivity::class.java)
                        intent.putExtra(ContainerActivity.EXTRA_HISTORY_ID, hisId)
                        startActivity(intent)
                        finish()
                    }
                })
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
//        startActivity(Intent(this, MainActivity::class.java))
        onBackPressed()
        return true
    }
    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}