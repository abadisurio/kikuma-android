package com.kikuma.kikumaapp.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity
import com.kikuma.kikumaapp.utils.DataDummy

class ProfileViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getAllHistory(): LiveData<List<HistoryEntity>> = homeRepository.getAllHistory()
}