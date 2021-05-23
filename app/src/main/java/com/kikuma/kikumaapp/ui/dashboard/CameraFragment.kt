package com.kikuma.kikumaapp.ui.dashboard

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.databinding.FragmentCameraBinding
import com.kikuma.kikumaapp.ui.confirmimage.ConfirmImageActivity
import com.kikuma.kikumaapp.ui.result.DiseaseResultActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment : Fragment() {
    private var imageCapture: ImageCapture? = null

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var fragmentCameraBinding: FragmentCameraBinding
//    private lateinit var cameraViewModel: CameraViewModel
    private lateinit var mFileUri: Uri

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        val root = inflater.inflate(R.layout.fragment_camera, container, false)

        fragmentCameraBinding = FragmentCameraBinding.inflate(layoutInflater)
        val root = fragmentCameraBinding.root

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        // Set up the listener for take photo button
        fragmentCameraBinding.cameraCaptureButton.setOnClickListener {
            takePhoto()
        }

        outputDirectory = getOutputDirectory()

        cameraExecutor = Executors.newSingleThreadExecutor()

        return root
    }

    private fun takePhoto() {
        Toast.makeText(requireActivity(), "pressed", Toast.LENGTH_SHORT).show()
        // Get a stable reference of the modifiable image capture use case
        Log.e(TAG, imageCapture.toString())
        val imageCapture = imageCapture ?: return

        // Create time-stamped output file to hold the image
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg")

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // Set up image capture listener, which is triggered after photo has
        // been taken

        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    mFileUri = savedUri
                    val msg = "Photo capture succeeded: $savedUri"
                    Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)
                    Log.d("filepathwkwk", savedUri.toString())
                    val intent = Intent(activity, DiseaseResultActivity::class.java)
                    intent.putExtra(DiseaseResultActivity.EXTRA_RESULT, savedUri.toString())
                    startActivity(intent)
//                    requireActivity().finish()

                }
            })
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        val viewFinder: PreviewView = fragmentCameraBinding.viewFinder

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            imageCapture = ImageCapture.Builder()
                .build()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun getOutputDirectory(): File {
        val mediaDir = requireContext().getExternalFilesDirs(null).first()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireContext().filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(requireActivity(),
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                activity?.finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_PERMISSIONS && resultCode == Activity.RESULT_OK){
            if(data != null){
                val filePath = data.data
                Log.d("filepathwkwk", filePath.toString())
                val intent = Intent(activity, DiseaseResultActivity::class.java)
                intent.putExtra(ConfirmImageActivity.EXTRA_IMAGE_URI, filePath.toString())
                startActivity(intent)
            }
//            val photo: Bitmap = data?.extras?.get("data") as Bitmap


//            val intent = Intent(requireContext(), ConfirmImageActivity::class.java)
//            intent.putExtra(ConfirmImageActivity.EXTRA_IMAGE_URI, savedUri)
//            requireContext().startActivity(intent)
        }
    }

}