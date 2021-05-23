package com.kikuma.kikumaapp.ui.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kikuma.kikumaapp.databinding.ActivityHospitalMapsBinding

class HospitalMapsActivity : AppCompatActivity() {

    private lateinit var hospitalMapsBinding: ActivityHospitalMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hospitalMapsBinding = ActivityHospitalMapsBinding.inflate(layoutInflater)

        setContentView(hospitalMapsBinding.root)
    }
}