package com.kikuma.kikumaapp.utils

import android.content.Context
import android.util.Log
import com.kikuma.kikumaapp.data.source.remote.response.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadArticles(): List<ArticleResponse> {
        val list = ArrayList<ArticleResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("ArticleResponses.json").toString())
            val listArray = responseObject.getJSONArray("articles")
            for (i in 0 until listArray.length()) {
                val article = listArray.getJSONObject(i)

                val id = article.getString("articleId")
                val title = article.getString("title")
                val description = article.getString("description")
                val imagePath = article.getString("imagePath")
                val posted = article.getString("posted")

                val articleResponse = ArticleResponse(id, title, description, imagePath, posted)
                list.add(articleResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }



    fun loadHistory(): List<HistoryResponse> {
        val list = ArrayList<HistoryResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("HistoryResponses.json").toString())
            val listArray = responseObject.getJSONArray("history")
            for (i in 0 until listArray.length()) {
                val history = listArray.getJSONObject(i)

                val historyId = history.getString("historyId")
                val disease = history.getString("disease")
                val imageData = history.getString("imageData")
                val posted = history.getString("posted")

                val historyResponse = HistoryResponse(historyId, disease, imageData, posted)
                list.add(historyResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadDetailArticle(articleId: String): List<ArticleResponse> {
        val fileName = String.format("ArticleResponses.json", articleId)
        val list = ArrayList<ArticleResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("articles")
                for (i in 0 until listArray.length()) {
                    val article = listArray.getJSONObject(i)

                    val id = article.getString("articleId")
                    val title = article.getString("title")
                    val description = article.getString("description")
                    val imagePath = article.getString("imagePath")
                    val posted = article.getString("posted")

                    val articleResponse = ArticleResponse(id, title,description, imagePath, posted)
                    list.add(articleResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadAllResult(): List<DiseaseResponse> {
        val list = ArrayList<DiseaseResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("ResultResponses.json").toString())
            val listArray = responseObject.getJSONArray("result")
            for (i in 0 until listArray.length()) {
                val result = listArray.getJSONObject(i)

                val id = result.getString("resultId")
                val disease = result.getString("disease")
                val description = result.getString("description")
                val percentage = result.getString("percentage")

                val diseaseResponse = DiseaseResponse(id, disease, description, percentage)
                list.add(diseaseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadDetailResult(resultId: String): List<DiseaseResponse> {
        val fileName = String.format("ResultResponses.json", resultId)
        val list = ArrayList<DiseaseResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("result")
                for (i in 0 until listArray.length()) {
                    val resultDisease = listArray.getJSONObject(i)

                    val resultId = resultDisease.getString("resultId")
                    val disease = resultDisease.getString("disease")
                    val description = resultDisease.getString("description")
                    val percentage = resultDisease.getString("percentage")

                    val diseaseResponse = DiseaseResponse(resultId, disease, description, percentage)
                    list.add(diseaseResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTips(resultId: String): List<TipsResponse> {
        val fileName = String.format("Tips_r1.json", resultId)
        val list = ArrayList<TipsResponse>()
        try {
            val result = parsingFileToString(fileName = fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("tips")
                for (i in 0 until listArray.length()) {
                    val tip = listArray.getJSONObject(i)

                    val tipsId = tip.getString("tipsId")
                    val resultId = tip.getString("resultId")
                    val tips = tip.getString("tips")
                    val forDisease = tip.getString("forDisease")

                    val tipsResponse = TipsResponse(tipsId, resultId, tips, forDisease)
                    list.add(tipsResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadHospital(): List<HospitalResponse> {
        val list = ArrayList<HospitalResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("HospitalResponses.json").toString())
            val listArray = responseObject.getJSONArray("hospital")
            for (i in 0 until listArray.length()) {
                val hospital = listArray.getJSONObject(i)

                val hospitalId = hospital.getString("hospitalId")
                val hospitalName = hospital.getString("hospital")
                val address = hospital.getString("address")
                val rate = hospital.getDouble("rate")
                val price = hospital.getString("price")
                val imagePath = hospital.getString("imagePath")

                val hospitalResponse = HospitalResponse(hospitalId, hospitalName, address, rate, price, imagePath)
                list.add(hospitalResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }
}