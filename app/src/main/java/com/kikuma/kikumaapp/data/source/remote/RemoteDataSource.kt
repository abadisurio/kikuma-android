package com.kikuma.kikumaapp.data.source.remote

import android.os.Handler
import android.os.Looper
import com.kikuma.kikumaapp.data.source.remote.response.ArticleResponse
import com.kikuma.kikumaapp.data.source.remote.response.HistoryResponse
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

    fun getAllArticles(callback: LoadArticleCallback) {
        handler.postDelayed({ callback.onAllArticlesReceived(jsonHelper.loadArticles()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllHistory(callback: LoadHistoryCallback) {
        handler.postDelayed({ callback.onAllHistoryReceived(jsonHelper.loadHistory()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadArticleCallback {
        fun onAllArticlesReceived(articleResponses: List<ArticleResponse>)
    }

    interface LoadHistoryCallback {
        fun onAllHistoryReceived(historyResponses: List<HistoryResponse>)
    }
}