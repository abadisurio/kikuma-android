package com.kikuma.kikumaapp.di

import android.content.Context
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.remote.RemoteDataSource
import com.kikuma.kikumaapp.utils.JsonHelper

object Injection {

    fun provideRepository(context: Context): HomeRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return HomeRepository.getInstance(remoteDataSource)
    }
}