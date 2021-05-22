package com.kikuma.kikumaapp.di

import android.content.Context
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.LocalDataSource
import com.kikuma.kikumaapp.data.source.local.room.HomeDatabase
import com.kikuma.kikumaapp.data.source.remote.RemoteDataSource
import com.kikuma.kikumaapp.utils.AppExecutors
import com.kikuma.kikumaapp.utils.JsonHelper

object Injection {

    fun provideRepository(context: Context): HomeRepository {

        val database = HomeDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.homeDao())
        val appExecutors = AppExecutors()

        return HomeRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}