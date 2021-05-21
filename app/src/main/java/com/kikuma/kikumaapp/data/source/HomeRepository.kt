package com.kikuma.kikumaapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity
import com.kikuma.kikumaapp.data.source.remote.RemoteDataSource
import com.kikuma.kikumaapp.data.source.remote.response.ArticleResponse
import com.kikuma.kikumaapp.data.source.remote.response.HistoryResponse

class HomeRepository private constructor(private val remoteDataSource: RemoteDataSource) : HomeDataSource{

    companion object {
        @Volatile
        private var instance: HomeRepository? = null
        fun getInstance(remoteData: RemoteDataSource): HomeRepository =
                instance ?: synchronized(this) {
                    instance ?: HomeRepository(remoteData).apply { instance = this }
                }
    }

    override fun getAllArticles(): LiveData<List<ArticleEntity>> {
        val articleResults = MutableLiveData<List<ArticleEntity>>()
        remoteDataSource.getAllArticles(object : RemoteDataSource.LoadArticleCallback {
            override fun onAllArticlesReceived(articleResponses: List<ArticleResponse>) {
                val articleList = ArrayList<ArticleEntity>()
                for (response in articleResponses) {
                    val article = ArticleEntity(response.articleId,
                            response.title,
                            response.description,
                            response.imagePath,
                            response.posted)
                    articleList.add(article)
                }
                articleResults.postValue(articleList)
            }
        })

        return articleResults
    }

    override fun getDetailArticle(articleId: String): LiveData<ArticleEntity> {
        val articleResult = MutableLiveData<ArticleEntity>()
        remoteDataSource.getAllArticles(object : RemoteDataSource.LoadArticleCallback {
            override fun onAllArticlesReceived(articleResponses: List<ArticleResponse>) {
                lateinit var article: ArticleEntity
                for (response in articleResponses) {
                    if (response.articleId == articleId) {
                        article = ArticleEntity(response.articleId,
                                response.title,
                                response.description,
                                response.imagePath,
                                response.posted)
                    }
                }
                articleResult.postValue(article)
            }
        })
        return articleResult
    }

    override fun getAllHistory(): LiveData<List<HistoryEntity>> {
        val historyResults = MutableLiveData<List<HistoryEntity>>()
        remoteDataSource.getAllHistory(object : RemoteDataSource.LoadHistoryCallback {
            override fun onAllHistoryReceived(historyResponses: List<HistoryResponse>) {
                val historyList = ArrayList<HistoryEntity>()
                for (response in historyResponses) {
                    val history = HistoryEntity(response.disease,
                            response.posted)
                    historyList.add(history)
                }
                historyResults.postValue(historyList)
            }
        })

        return historyResults
    }
}