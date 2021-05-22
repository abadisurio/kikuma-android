package com.kikuma.kikumaapp.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kikuma.kikumaapp.data.source.remote.response.ArticleResponse
import com.kikuma.kikumaapp.data.source.remote.response.HistoryResponse
import com.kikuma.kikumaapp.utils.EspressoIdlingResource
import com.kikuma.kikumaapp.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper).apply { instance = this }
                }
    }

    fun getAllArticles(): LiveData<ApiResponse<List<ArticleResponse>>> {
        EspressoIdlingResource.increment()
        val resultArticle = MutableLiveData<ApiResponse<List<ArticleResponse>>>()
        handler.postDelayed({
            resultArticle.value = ApiResponse.success(jsonHelper.loadArticles())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultArticle
    }

    fun getAllHistory(): LiveData<ApiResponse<List<HistoryResponse>>> {
        EspressoIdlingResource.increment()
        val resultHistory = MutableLiveData<ApiResponse<List<HistoryResponse>>>()
        handler.postDelayed({
            resultHistory.value = ApiResponse.success(jsonHelper.loadHistory())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultHistory
    }

    fun getDetailArticle(articleId: String): LiveData<ApiResponse<List<ArticleResponse>>> {
        EspressoIdlingResource.increment()
        val resultArticle = MutableLiveData<ApiResponse<List<ArticleResponse>>>()
        handler.postDelayed({
            resultArticle.value = ApiResponse.success(jsonHelper.loadDetailArticle(articleId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultArticle
    }
}