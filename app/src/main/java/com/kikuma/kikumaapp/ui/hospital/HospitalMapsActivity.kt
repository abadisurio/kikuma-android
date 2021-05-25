package com.kikuma.kikumaapp.ui.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.kikuma.kikumaapp.BuildConfig
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.databinding.ActivityHospitalMapsBinding

class HospitalMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mPlaces: Unit

    private lateinit var hospitalMapsBinding: ActivityHospitalMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hospitalMapsBinding = ActivityHospitalMapsBinding.inflate(layoutInflater)
        setContentView(hospitalMapsBinding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_hospital) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mPlaces = Places.initialize(this, BuildConfig.MAPS_API_KEY)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-6.2615, 106.8106)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Jakarta"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}