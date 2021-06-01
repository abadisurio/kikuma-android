package com.kikuma.kikumaapp.ui.confirmimage

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.kikuma.kikumaapp.Api.ApiConfig
import com.kikuma.kikumaapp.Api.RawApiResponse
import com.kikuma.kikumaapp.data.source.HomeRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.format
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.full.memberProperties

class ConfirmImageViewModel(private val homeRepository: HomeRepository): ViewModel() {

    private fun firestoreInstance(): FirebaseFirestore {
        // Access a Cloud Firestore instance from your Activity
        val db = FirebaseFirestore.getInstance()
//        val articleCollectionRef = db.collection("articles")
        return db
    }

    companion object {
        const val TAG = "ConfirmImageViewModel"
    }

    val historyId = MutableLiveData<String>()
    val note = MutableLiveData<String>()
    val modelResult = MutableLiveData<RawApiResponse>()

    val isLoading = MutableLiveData<Boolean>()
    val isSuccess = MutableLiveData(false)


    val imageBase64 = MutableLiveData<String>()

    private var auth: FirebaseAuth = Firebase.auth

    fun setImageBase64(articleId: String) {
        this.imageBase64.value = articleId
    }

    fun setNotes(note: String){
        this.note.value = note
    }

    fun uploadImage(){
//        setHistoryToFirebase()
        isLoading.value = true
        Log.d("hmm", imageBase64.value.toString())
        if(imageBase64.value != null){

            val body = getImageBase64Json(imageBase64.value.toString())
                .trimIndent().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

            val client = ApiConfig.getApiService().uploadImage(body)
            client.enqueue(object : Callback<RawApiResponse> {
                override fun onResponse(
                    call: Call<RawApiResponse>,
                    response: Response<RawApiResponse>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        Log.d("ini rawModelResult", response.body().toString())
                        modelResult.value = response.body()
                        runBlocking {
                            launch {
                                setHistoryToFirebase()
                            }
                        }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<RawApiResponse>, t: Throwable) {
                    isLoading.value = false
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
    }

    private fun getImageBase64Json(imageData: String): String{
        var json = JSONObject()
        json.put("image", imageData)
        return json.toString(4)
    }

    fun setHistoryToFirebase(){
        val documentId = MutableLiveData<String>()
        val historyRef = firestoreInstance()
            .collection("scan-history")
            .document(auth.currentUser?.uid!!)
            .collection("history")
        val data = hashMapOf(
            "disease" to note,
            "imageData" to imageBase64.value,
            "posted" to System.currentTimeMillis() / 1000L
//                    "Friday, 4 Juni 2021"
//                    SimpleDateFormat("EEEE, d MMMM yyyy", Locale.US).format(Date())
        )
        historyRef
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d("ini docRef success", documentReference.id)
                documentId.value = documentReference.id
                historyId.value = documentReference.id
                setModelResultToFirebase(documentReference.id)
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }
    private fun setModelResultToFirebase(historyId: String){
        val modelBatchRef = firestoreInstance()
            .batch()

        val acneName = hashMapOf(
            "acneComedonal" to "Acne Comedonal",
            "acneVulgaris" to "Acne Vulgaris"
        )

        if(modelResult.value != null){

            RawApiResponse::class.memberProperties.forEach { member ->
                Log.d("ini nama", member.name)
                val percentage = member.get(modelResult.value!!)
                val data = hashMapOf(
                    "resultId" to FieldValue.increment(1),
                    "historyParent" to historyId,
                    "disease" to acneName[member.name],
                    "percentage" to "%.0f%%".format(percentage)
                )
                val docRef = firestoreInstance()
                    .collection("scan-history")
                    .document(auth.currentUser?.uid!!)
                    .collection("model-result")
                    .document()
                modelBatchRef.set(docRef, data)
            }
            modelBatchRef.commit().addOnSuccessListener {
                isSuccess.value = true
            }
        }

    }

}