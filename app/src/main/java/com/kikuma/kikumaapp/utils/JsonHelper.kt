package com.kikuma.kikumaapp.utils

import android.content.Context
import com.kikuma.kikumaapp.data.source.remote.response.ArticleResponse
import com.kikuma.kikumaapp.data.source.remote.response.DiseaseResponse
import com.kikuma.kikumaapp.data.source.remote.response.HistoryResponse
import com.kikuma.kikumaapp.data.source.remote.response.TipsResponse
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

    fun loadResult(): List<DiseaseResponse> {
        val list = ArrayList<DiseaseResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("ResultResponses.json").toString())
            val listArray = responseObject.getJSONArray("result")
            for (i in 0 until listArray.length()) {
                val result = listArray.getJSONObject(i)

                val resultId = result.getString("resultId")
                val disease = result.getString("disease")
                val description = result.getString("description")

                val resultResponse = DiseaseResponse(resultId, disease, description)
                list.add(resultResponse)
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
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("tips")
                for (i in 0 until listArray.length()) {
                    val tip = listArray.getJSONObject(i)

                    val tipsId = tip.getString("tipsId")
                    val tips = tip.getString("tips")

                    val tipsResponse = TipsResponse(tipsId, resultId, tips)
                    list.add(tipsResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}