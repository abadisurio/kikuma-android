package com.kikuma.kikumaapp.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kikuma.kikumaapp.databinding.DiseaseResultBinding
import com.kikuma.kikumaapp.ui.hospital.HospitalMapsActivity

class DiseaseResultActivity : AppCompatActivity() {

    private lateinit var diseaseResultBinding: DiseaseResultBinding

    companion object {
        const val EXTRA_HISTORY_ID = "extra_history_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        diseaseResultBinding = DiseaseResultBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(diseaseResultBinding.root)

        diseaseResultBinding.btnRs.setOnClickListener {
            val intent = Intent(this, HospitalMapsActivity::class.java)
            startActivity(intent)
        }

    }
}