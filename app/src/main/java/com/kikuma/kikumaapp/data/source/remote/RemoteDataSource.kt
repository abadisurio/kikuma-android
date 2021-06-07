package com.kikuma.kikumaapp.data.source.remote

import android.content.ContentValues.TAG
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kikuma.kikumaapp.data.source.remote.response.*
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
                                response.data?.get("description").toString().replace("\\n", "\n"),
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

    //all result
    fun getAllResult(): LiveData<ApiResponse<List<DiseaseResponse>>> {
        val resultDisease = MutableLiveData<ApiResponse<List<DiseaseResponse>>>()
        resultDisease.value = ApiResponse.success(jsonHelper.loadAllResult())
        return resultDisease
    }

    //detail result
    fun getDetailResult(resultId: String): LiveData<ApiResponse<List<DiseaseResponse>>> {
        val resultArticle = MutableLiveData<ApiResponse<List<DiseaseResponse>>>()
        resultArticle.value = ApiResponse.success(jsonHelper.loadDetailResult(resultId))
        return resultArticle
    }

    //tips
    fun getTipsForDisease(forDisease: String): LiveData<ApiResponse<List<TipsResponse>>> {
        val resultTips = MutableLiveData<ApiResponse<List<TipsResponse>>>()
        resultTips.value = ApiResponse.success(jsonHelper.loadTips(forDisease))
        return resultTips
    }

    //hospital
    fun getAllHospital(): LiveData<ApiResponse<List<HospitalResponse>>> {
        val resultHospital = MutableLiveData<ApiResponse<List<HospitalResponse>>>()
        resultHospital.value = ApiResponse.success(jsonHelper.loadHospital())
        return resultHospital
    }
}