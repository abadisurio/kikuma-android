package com.kikuma.kikumaapp.data.source.remote

import android.content.ContentValues.TAG
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.engine.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.kikuma.kikumaapp.data.source.remote.response.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.kikuma.kikumaapp.data.source.local.entity.ModelResultEntity
import com.kikuma.kikumaapp.data.source.remote.response.ArticleResponse
import com.kikuma.kikumaapp.data.source.remote.response.DiseaseResponse
import com.kikuma.kikumaapp.data.source.remote.response.HistoryResponse
import com.kikuma.kikumaapp.data.source.remote.response.TipsResponse
import com.kikuma.kikumaapp.utils.EspressoIdlingResource
import com.kikuma.kikumaapp.utils.JsonHelper
import kotlin.reflect.typeOf

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    private var auth: FirebaseAuth = Firebase.auth

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
            .document(auth.currentUser?.uid!!)
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
        val articleRef = firestoreInstance().collection("disease")
        articleRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("wkwk", " => " + task.result)
                    val documents = task.result?.documents
                    if(documents != null){
                        val articleList = ArrayList<DiseaseResponse>()
                        for (response in documents) {
                            Log.d("ini poster", response.id + " => " + response.data?.get("imagePath"))
                            val document = DiseaseResponse(
                                response.id,
                                response.data?.get("disease").toString(),
                                response.data?.get("description").toString(),
                                response.data?.get("imagePath").toString(),
                            )
                            articleList.add(document)
                        }
                        resultDisease.value = ApiResponse.success(articleList)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
        return resultDisease
    }

    //detail result
    fun getDetailDisease(resultId: String): LiveData<ApiResponse<List<DiseaseResponse>>> {
        val resultDisease = MutableLiveData<ApiResponse<List<DiseaseResponse>>>()
        val diseaseRef = firestoreInstance().collection("diseases")
        diseaseRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("wkwk", " => " + task.result)
                    val documents = task.result?.documents
                    if(documents != null){
                        val diseaseList = ArrayList<DiseaseResponse>()
                        for (response in documents) {
                            Log.d("ini poster", response.id)
                            if(response.data?.get("disease").toString() == resultId){
                                Log.d("ini poster2", response.id + " => " + response.data?.get("imagePath"))
                                val document = DiseaseResponse(
                                    response.id,
                                    response.data?.get("disease").toString(),
                                    response.data?.get("description").toString(),
                                    response.data?.get("imagePath").toString(),
                                )
                                diseaseList.add(document)
                            }
                        }
                        resultDisease.value = ApiResponse.success(diseaseList)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
        return resultDisease
    }
    //model result
    fun getModelResult(): LiveData<ApiResponse<List<ModelResultResponse>>> {
        val resultModelResult = MutableLiveData<ApiResponse<List<ModelResultResponse>>>()
        val modelResultRef = firestoreInstance()
            .collection("scan-history")
            .document(auth.currentUser?.uid!!)
            .collection("model-result")
        modelResultRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("ini modelResult", " => " + task.result?.documents)
                    val documents = task.result?.documents
                    if(documents != null){
                        val modelResultList = ArrayList<ModelResultResponse>()
                        for (response in documents) {
                            Log.d("ini poster", response.id + " => " + response.data?.get("imageData"))
                            val document = ModelResultResponse(
                                response.id,
                                response.data?.get("historyParent").toString(),
                                response.data?.get("disease").toString(),
                                response.data?.get("percentage").toString(),
                            )
                            modelResultList.add(document)
                        }
                        resultModelResult.value = ApiResponse.success(modelResultList)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
        return resultModelResult
    }
    fun setModelResult(imageBase64: String): LiveData<String>{
        val documentId = MutableLiveData<String>()
        val historyRef = firestoreInstance()
            .collection("scan-history")
            .document(auth.currentUser?.uid!!)
            .collection("history")
        val data = hashMapOf(
            "disease" to "Tokyo",
            "imageData" to imageBase64,
            "posted" to "Saturday, 8 May 2021 13:42"
        )
        historyRef
            .add(data)
            .addOnSuccessListener { documentReference ->
                documentId.value = documentReference.id
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        return documentId
    }

    //tips
    fun getTipsForDisease(forDisease: String): LiveData<ApiResponse<List<TipsResponse>>> {
        val resultTips = MutableLiveData<ApiResponse<List<TipsResponse>>>()
//        resultTips.value = ApiResponse.success(jsonHelper.loadTips(forDisease))
//        return resultTips

        val tipsRef = firestoreInstance()
            .collection("tips")
        tipsRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("ini tips", " => " + task.result)
                    val documents = task.result?.documents
                    if(documents != null){
                        val tipsList = ArrayList<TipsResponse>()
                        for (response in documents) {
                            Log.d("ini poster", response.id + " => " + response.data?.get("imageData"))
                            val document = TipsResponse(
                                response.id,
                                response.data?.get("resultId").toString(),
                                response.data?.get("tips").toString(),
                                response.data?.get("forDisease").toString(),
                            )
                            tipsList.add(document)
                        }
                        resultTips.value = ApiResponse.success(tipsList)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
        return resultTips

    }

    //hospital
    fun getAllHospital(): LiveData<ApiResponse<List<HospitalResponse>>> {
        val resultHospital = MutableLiveData<ApiResponse<List<HospitalResponse>>>()
        resultHospital.value = ApiResponse.success(jsonHelper.loadHospital())
//        return resultHospital
        val hospitalRef = firestoreInstance().collection("hospitals")
        hospitalRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("wkwk", " => " + task.result)
                    val documents = task.result?.documents
                    if(documents != null){
                        val hospitalList = ArrayList<HospitalResponse>()
                        for (response in documents) {
                            Log.d("ini poster", response.id + " => " + response.data?.get("imagePath"))
                            val document = HospitalResponse(
                                response.data?.get("hospitalId").toString(),
                                response.data?.get("hospital").toString(),
                                response.data?.get("address").toString(),
                                response.data?.get("rate").toString().toDouble(),
                                response.data?.get("price").toString(),
                                response.data?.get("imagePath").toString(),
                            )
                            hospitalList.add(document)
                        }
                        resultHospital.value = ApiResponse.success(hospitalList)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
        }
        return resultHospital
    }


}