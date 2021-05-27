package com.kikuma.kikumaapp.ui.result

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.data.source.local.entity.DiseaseEntity
import com.kikuma.kikumaapp.databinding.ActivityDiseaseResultBinding
import com.kikuma.kikumaapp.databinding.DiseaseResultBinding
import com.kikuma.kikumaapp.databinding.FragmentDiseaseResultBinding
import com.kikuma.kikumaapp.ui.hospital.NearestHospitalFragment
import com.kikuma.kikumaapp.ui.result.list.ListResultFragment
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory

class DiseaseResultFragment : Fragment() {

    companion object {
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_HISTORY_ID = "extra_history_id"
    }

    private lateinit var diseaseResultBinding: DiseaseResultBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val fragmentDiseaseResultBinding = FragmentDiseaseResultBinding.inflate(layoutInflater, container, false)
        diseaseResultBinding = fragmentDiseaseResultBinding.diseaseResult
        return fragmentDiseaseResultBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ResultAdapter()

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[DiseaseResultViewModel::class.java]

        //val extras = intent.extras
        val bundle = this.arguments
        if (bundle != null) {
            val resultId = bundle.getString(EXTRA_RESULT)
            if (resultId != null) {
                diseaseResultBinding.progressBar.visibility = View.VISIBLE

                viewModel.setResultCourse(resultId)
                viewModel.getTips().observe(viewLifecycleOwner, { tips ->
                    diseaseResultBinding.progressBar.visibility = View.GONE
                    adapter.setTips(tips)
                    adapter.notifyDataSetChanged()
                })

                viewModel.getResult().observe(viewLifecycleOwner, { result -> populateResult(result) })
            }
        }

        with(diseaseResultBinding.rvTips) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun populateResult(diseaseEntity: DiseaseEntity) {
        diseaseResultBinding.tvDiseaseName.text = diseaseEntity.disease
        diseaseResultBinding.tvDescription.text = diseaseEntity.description

        diseaseResultBinding.btnHospital.setOnClickListener {
            val mNearHospitalFragment = NearestHospitalFragment()

            val mBundle = Bundle()
            mBundle.putString(NearestHospitalFragment.EXTRA_HOSPITAL, diseaseEntity.toString())
            mNearHospitalFragment.arguments = mBundle

            val mFragmentManager = fragmentManager
            mFragmentManager?.beginTransaction()?.apply {
                replace(
                    R.id.frame_container,
                    mNearHospitalFragment,
                    NearestHospitalFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
            /*
            val mFragmentManager = fragmentManager
            val mNearHospitalFragment = NearestHospitalFragment()
            val fragment = mFragmentManager?.findFragmentByTag(NearestHospitalFragment::class.java.simpleName)
            if (fragment !is ListResultFragment) {
                Log.d("MyFlexibleFragment", "Fragment Name :" + NearestHospitalFragment::class.java.simpleName)
                mFragmentManager?.beginTransaction()
                    ?.add(R.id.frame_container, mNearHospitalFragment, NearestHospitalFragment::class.java.simpleName)
                    ?.commit()
            }

             */
        }
    }
}