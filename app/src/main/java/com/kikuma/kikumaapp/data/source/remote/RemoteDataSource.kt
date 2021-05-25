package com.kikuma.kikumaapp.data.source.remote

import android.content.ContentValues.TAG
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.kikuma.kikumaapp.data.source.remote.response.ArticleResponse
import com.kikuma.kikumaapp.data.source.remote.response.DiseaseResponse
import com.kikuma.kikumaapp.data.source.remote.response.HistoryResponse
import com.kikuma.kikumaapp.data.source.remote.response.TipsResponse
import com.kikuma.kikumaapp.utils.EspressoIdlingResource
import com.kikuma.kikumaapp.utils.JsonHelper
import kotlin.reflect.typeOf

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

    private fun firestoreInstance(): FirebaseFirestore {
        // Access a Cloud Firestore instance from your Activity
        val db = FirebaseFirestore.getInstance()
//        val articleCollectionRef = db.collection("articles")
        return db
    }

    fun getAllArticles(): LiveData<ApiResponse<List<ArticleResponse>>> {
        val resultArticle = MutableLiveData<ApiResponse<List<ArticleResponse>>>()
        val articleRef = firestoreInstance().collection("articles")
        articleRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("wkwk", " => " + task.result)
                    val documents = task.result?.documents
                    if(documents != null){
                        val articleList = ArrayList<ArticleResponse>()
                        for (response in documents) {
                            Log.d("ini poster", response.id + " => " + response.data?.get("imagePath"))
                            val document = ArticleResponse(
                                response.id,
                                response.data?.get("title").toString(),
                                response.data?.get("description").toString(),
                                response.data?.get("imagePath").toString(),
                                response.data?.get("posted").toString(),
                            )
                            articleList.add(document)
                        }
                        resultArticle.value = ApiResponse.success(articleList)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
        return resultArticle
    }

    fun getAllHistory(): LiveData<ApiResponse<List<HistoryResponse>>> {
        val resultHistory = MutableLiveData<ApiResponse<List<HistoryResponse>>>()
        val historyRef = firestoreInstance()
            .collection("scan-history")
            .document("3wjrUo7Ak7pYGrSsAFHw")
            .collection("history")
        historyRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("ini history", " => " + task.result)
                    val documents = task.result?.documents
                    if(documents != null){
                        val historyList = ArrayList<HistoryResponse>()
                        for (response in documents) {
                            Log.d("ini poster", response.id + " => " + response.data?.get("imageData"))
                            val document = HistoryResponse(
                                response.id,
                                response.data?.get("disease").toString(),
                                response.data?.get("imageData").toString(),
                                response.data?.get("posted").toString(),
                            )
                            historyList.add(document)
                        }
                        resultHistory.value = ApiResponse.success(historyList)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
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

    fun getResult(callback: LoadResultCallback) {
        handler.postDelayed({ callback.onAllResultReceived(jsonHelper.loadResult()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadResultCallback {
        fun onAllResultReceived(diseaseResponse: List<DiseaseResponse>)
    }

    fun getTips(resultId: String, callback: LoadTipsCallback){
        handler.postDelayed({ callback.onAllTipsReceived(jsonHelper.loadTips(resultId)) }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadTipsCallback {
        fun onAllTipsReceived(tipsResponse: List<TipsResponse>)
    }
}