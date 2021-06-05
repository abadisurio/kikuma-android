package com.kikuma.kikumaapp.ui.confirmimage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kikuma.kikumaapp.databinding.ActivityConfirmImageBinding
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory
import java.io.ByteArrayOutputStream


class ConfirmImageActivity : AppCompatActivity() {
    lateinit var activityConfirmImageBinding: ActivityConfirmImageBinding

    lateinit var imageUri: String
    lateinit var imageDir: String
    var backDisabled = false

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image"
        const val EXTRA_IMAGE_DIR = "extra_image_dir"
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
            imageDir = extras.getString(EXTRA_IMAGE_DIR).toString()
            Log.d("wkwk", imageUri)
            imageView.setImageURI(Uri.parse(imageUri))
        }
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[ConfirmImageViewModel::class.java]

        val edNote = activityConfirmImageBinding.edNote

        activityConfirmImageBinding.btnNext.setOnClickListener{
//            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.content, ListResultFragment())
//            transaction.commit()
            Log.d("hehe", TextUtils.isEmpty(edNote.text).toString())
            if(TextUtils.isEmpty(edNote.text)){
                edNote.error = "Required!"
            }else{
                updateUI()
                val imageStream = contentResolver.openInputStream(Uri.parse(imageUri))
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                val encodedImage = encodeImage(selectedImage)
                Log.d("wgwg2", encodedImage.toString())
                if(encodedImage!=null){
                    viewModel.setNotes(edNote.text.toString())
                    viewModel.setImageBase64(encodedImage)
                    viewModel.uploadImage()
                    viewModel.isSuccess.observe(this, {
                        if (it) {
                            val hisId = viewModel.historyId.value.toString()
                            val intent = Intent(this, ContainerActivity::class.java)
                            intent.putExtra(ContainerActivity.EXTRA_HISTORY_ID, hisId)
                            startActivity(intent)
                            finish()
                        }
                    })
                }
            }

        }
    }
    override fun onSupportNavigateUp(): Boolean {
//        startActivity(Intent(this, MainActivity::class.java))
        onBackPressed()
        return true
    }
    private fun encodeImage(bm: Bitmap): String? {

        val ei = ExifInterface(imageDir)
        val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED)

        val rotatedBitmap: Bitmap = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bm, 90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bm, 180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bm, 270f)
            ExifInterface.ORIENTATION_NORMAL -> bm
            else -> bm
        }

        val baos = ByteArrayOutputStream()
        rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
    private fun updateUI(){
        activityConfirmImageBinding.loading.layoutLoading.visibility = View.VISIBLE
        supportActionBar?.hide()
        backDisabled = true
    }

    override fun onBackPressed() {
        if(!backDisabled){
            super.onBackPressed()
        }
    }
    fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height,
                matrix, true)
    }

}