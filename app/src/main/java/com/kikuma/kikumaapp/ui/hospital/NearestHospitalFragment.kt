package com.kikuma.kikumaapp.ui.hospital

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.databinding.FragmentNearestHospitalBinding
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory

class NearestHospitalFragment : Fragment() {

    companion object {
        const val EXTRA_HOSPITAL = "extra_hospital"
    }

    private lateinit var fragmentNearestHospitalBinding: FragmentNearestHospitalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentNearestHospitalBinding = FragmentNearestHospitalBinding.inflate(layoutInflater, container, false)
        return fragmentNearestHospitalBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[NearestHospitalViewModel::class.java]

        val hospitalAdapter = NearestHospitalAdapter()
        fragmentNearestHospitalBinding.progressBar.visibility = View.VISIBLE
        viewModel.getNearestHospital().observe(viewLifecycleOwner, { hospital ->
            fragmentNearestHospitalBinding.progressBar.visibility = View.GONE
            hospitalAdapter.setHospitals(hospital.data)
            hospitalAdapter.notifyDataSetChanged()
        })

        with(fragmentNearestHospitalBinding.rvRs) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = hospitalAdapter
        }
    }
}