package com.kikuma.kikumaapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.di.Injection
import com.kikuma.kikumaapp.ui.detailarticle.DetailArticleViewModel
import com.kikuma.kikumaapp.ui.home.HomeViewModel
import com.kikuma.kikumaapp.ui.notifications.ProfileViewModel

class ViewModelFactory private constructor(private val mHomeRepository: HomeRepository) : ViewModelProvider.NewInstanceFactory(){

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                        instance = this
                    }
                }
    }

    @Suppress("UNCHECKED_LIST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(mHomeRepository) as T
            }
            modelClass.isAssignableFrom(DetailArticleViewModel::class.java) -> {
                return DetailArticleViewModel(mHomeRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                return ProfileViewModel(mHomeRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}