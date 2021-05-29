package com.kikuma.kikumaapp.ui.dashboard

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.databinding.FragmentCameraBinding
import com.kikuma.kikumaapp.ui.confirmimage.ConfirmImageActivity
import com.kikuma.kikumaapp.ui.result.DiseaseResultFragment
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
    private lateinit var preview: Preview
    private lateinit var cameraProvider: ProcessCameraProvider
    private var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private var isRearCamera = false


//    private lateinit var cameraViewModel: CameraViewModel
    private lateinit var mFileUri: Uri

    private val requestMultiplePermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                permissions.entries.forEach {
                    Log.d(TAG, "${it.key} = ${it.value}")
                }
                if (allPermissionsGranted()) {
                    Log.d(TAG, "Permission granted")
                    startCamera()
                } else {
                    Log.d(TAG, "Permission not granted")
                    requireActivity().finish()
                }
            }

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
//        const val REQUEST_CODE_PERMISSIONS = 10
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
            requestMultiplePermissions.launch( REQUIRED_PERMISSIONS )

        }

        // Set up the listener for take photo button
        fragmentCameraBinding.cameraCaptureButton.setOnClickListener {
            takePhoto()
        }

        fragmentCameraBinding.cameraFlipButton.setOnClickListener {
            flipCamera()
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

        // freeze preview when capture
        cameraProvider.unbind(preview)

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                @SuppressLint("ResourceType")
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    mFileUri = savedUri
                    val msg = "Photo capture succeeded: $savedUri"
                    Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)
                    Log.d("filepathwkwk", savedUri.toString())

                    val intent = Intent(activity, ConfirmImageActivity::class.java)
                    intent.putExtra(ConfirmImageActivity.EXTRA_IMAGE_URI, savedUri.toString())
                    startActivity(intent)
//                    requireActivity().finish()

                }
            })
    }

    private fun flipCamera() {
        cameraSelector = when(isRearCamera){
            true -> CameraSelector.DEFAULT_BACK_CAMERA
            false -> CameraSelector.DEFAULT_FRONT_CAMERA
        }
        isRearCamera = !isRearCamera
        startCamera()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        val viewFinder: PreviewView = fragmentCameraBinding.viewFinder
        lateinit var cameraLifecycle: Camera


        cameraProviderFuture.addListener({

            // Used to bind the lifecycle of cameras to the lifecycle owner
            cameraProvider = cameraProviderFuture.get()

            imageCapture = ImageCapture.Builder()
                .build()

            // Preview
            preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }

            try {


                Toast.makeText(requireActivity(),
                        "DI SINI BUKAN",
                        Toast.LENGTH_SHORT).show()

                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraLifecycle = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)
                setupZoomAndTapToFocus(cameraLifecycle, viewFinder)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))


    }

    fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun getOutputDirectory(): File {
        val mediaDir = requireContext().getExternalFilesDirs(null).first()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireContext().filesDir
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupZoomAndTapToFocus(cameraLifecycle: Camera, viewFinder: PreviewView) {
        val listener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val currentZoomRatio: Float = cameraLifecycle.cameraInfo.zoomState.value?.zoomRatio ?: 1F
                val delta = detector.scaleFactor
                cameraLifecycle.cameraControl.setZoomRatio(currentZoomRatio * delta)
                return true
            }
        }
        val scaleGestureDetector = ScaleGestureDetector(context, listener)

        viewFinder.setOnTouchListener { view: View, motionEvent: MotionEvent ->
            scaleGestureDetector.onTouchEvent(motionEvent)
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    // Get the MeteringPointFactory from PreviewView
                    val factory = viewFinder.meteringPointFactory

                    // Create a MeteringPoint from the tap coordinates
                    val point = factory.createPoint(motionEvent.x, motionEvent.y)

                    // Create a MeteringAction from the MeteringPoint, you can configure it to specify the metering mode
                    val action = FocusMeteringAction.Builder(point).build()

                    // Trigger the focus and metering. The method returns a ListenableFuture since the operation
                    // is asynchronous. You can use it get notified when the focus is successful or if it fails.
                    cameraLifecycle.cameraControl.startFocusAndMetering(action)

                    return@setOnTouchListener true
                }
                else -> return@setOnTouchListener false
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onResume() {
        super.onResume()
        startCamera()
    }
}