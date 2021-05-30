package com.kikuma.kikumaapp.ui.hospital

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.HospitalEntity
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.vo.Resource

class NearestHospitalViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getNearestHospital(): LiveData<Resource<List<HospitalEntity>>> = homeRepository.getAllHospital()
}