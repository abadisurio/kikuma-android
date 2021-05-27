package com.kikuma.kikumaapp.ui.hospital

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.HospitalEntity
import com.kikuma.kikumaapp.utils.DataDummy

class NearestHospitalViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getNearestHospital(): LiveData<List<HospitalEntity>> = homeRepository.getHospital()
}