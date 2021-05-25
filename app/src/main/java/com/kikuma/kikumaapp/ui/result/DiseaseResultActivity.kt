package com.kikuma.kikumaapp.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.kikuma.kikumaapp.MapsActivity
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.data.source.local.entity.DiseaseEntity
import com.kikuma.kikumaapp.databinding.ActivityDiseaseResultBinding
import com.kikuma.kikumaapp.databinding.DiseaseResultBinding
import com.kikuma.kikumaapp.ui.hospital.HospitalMapsActivity
import com.kikuma.kikumaapp.ui.hospital.NearestHospitalFragment
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory

class DiseaseResultActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HISTORY_ID = "extra_history_id"
    }
    

    private lateinit var diseaseResultBinding: DiseaseResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDiseaseResultBinding = ActivityDiseaseResultBinding.inflate(layoutInflater)
        diseaseResultBinding = activityDiseaseResultBinding.diseaseResult

        setContentView(activityDiseaseResultBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = ResultAdapter()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DiseaseResultViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val resultId = extras.getString(EXTRA_HISTORY_ID)
            if (resultId != null) {
                diseaseResultBinding.progressBar.visibility = View.VISIBLE

                viewModel.setResultCourse(resultId)
                viewModel.getTips().observe(this, { tips ->
                    diseaseResultBinding.progressBar.visibility = View.GONE
                    adapter.setTips(tips)
                    adapter.notifyDataSetChanged()
                })

                viewModel.getResult().observe(this, { result -> populateResult(result)})
            }
        }

        with(diseaseResultBinding.rvTips) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DiseaseResultActivity)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun populateResult(diseaseEntity : DiseaseEntity) {
        diseaseResultBinding.tvDiseaseName.text = diseaseEntity.disease
        diseaseResultBinding.tvDescription.text = diseaseEntity.description

        diseaseResultBinding.btnHospital.setOnClickListener {
            val intent = Intent(this, HospitalMapsActivity::class.java)
            startActivity(intent)
        }
    }
}