package com.kikuma.kikumaapp.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.vo.Resource

class ProfileViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getAllHistory(): LiveData<Resource<PagedList<HistoryEntity>>> = homeRepository.getAllHistory()
    fun refreshAllHistory(): LiveData<Resource<PagedList<HistoryEntity>>> = homeRepository.refreshAllHistory()
}