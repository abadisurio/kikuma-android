package com.kikuma.kikumaapp.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kikuma.kikumaapp.data.source.remote.response.*
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

    //all result
    fun getAllResult(callback: LoadAllResultCallback) {
        handler.postDelayed({ callback.onAllResultReceived(jsonHelper.loadAllResult()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadAllResultCallback {
        fun onAllResultReceived(diseaseResponse: List<DiseaseResponse>)
    }

    //detail result
    /*
    fun getResult(callback: LoadResultCallback, resultId: String) {
        handler.postDelayed({ callback.onResultReceived(jsonHelper.loadResult(resultId)) }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadResultCallback {
        fun onResultReceived(diseaseResponse: List<DiseaseResponse>)
    }
     */

    //tips
    fun getTips(resultId: String, callback: LoadTipsCallback){
        handler.postDelayed({ callback.onAllTipsReceived(jsonHelper.loadTips(resultId)) }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadTipsCallback {
        fun onAllTipsReceived(tipsResponse: List<TipsResponse>)
    }

    //hospital
    fun getHospital(callback: LoadHospitalCallback) {
        handler.postDelayed({ callback.onAllHospitalReceived(jsonHelper.loadHospital()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadHospitalCallback {
        fun onAllHospitalReceived(hospitalResponse: List<HospitalResponse>)
    }
}