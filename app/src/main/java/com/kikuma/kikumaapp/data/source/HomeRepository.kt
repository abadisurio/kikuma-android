package com.kikuma.kikumaapp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kikuma.kikumaapp.data.NetworkBoundResource
import com.kikuma.kikumaapp.data.source.local.LocalDataSource
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity
import com.kikuma.kikumaapp.data.source.remote.ApiResponse
import com.kikuma.kikumaapp.data.source.remote.RemoteDataSource
import com.kikuma.kikumaapp.data.source.remote.response.ArticleResponse
import com.kikuma.kikumaapp.data.source.remote.response.HistoryResponse
import com.kikuma.kikumaapp.utils.AppExecutors
import com.kikuma.kikumaapp.vo.Resource

class HomeRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : HomeDataSource{

    companion object {
        @Volatile
        private var instance: HomeRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): HomeRepository =
                instance ?: synchronized(this) {
                    instance ?: HomeRepository(remoteData, localData, appExecutors).apply { instance = this }
                }
    }

    override fun getAllArticles(): LiveData<Resource<PagedList<ArticleEntity>>> {
        return object : NetworkBoundResource<PagedList<ArticleEntity>, List<ArticleResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ArticleEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllArticles(), config).build()
            }

            override fun shouldFetch(data: PagedList<ArticleEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ArticleResponse>>> =
                remoteDataSource.getAllArticles()

            public override fun saveCallResult(data: List<ArticleResponse>) {
                val articleList = ArrayList<ArticleEntity>()
                for (response in data) {
                    val article = ArticleEntity(response.articleId,
                        response.title,
                        response.description,
                        response.imagePath,
                        response.posted)
                    articleList.add(article)
                }

                localDataSource.insertArticle(articleList)
            }
        }.asLiveData()
    }

    override fun getDetailArticle(articleId: String): LiveData<Resource<ArticleEntity>> {
        return object : NetworkBoundResource<ArticleEntity, List<ArticleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<ArticleEntity> =
                localDataSource.getDetailArticle(articleId)

            override fun shouldFetch(data: ArticleEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<ArticleResponse>>> =
                remoteDataSource.getDetailArticle(articleId)

            override fun saveCallResult(data: List<ArticleResponse>) {
                val articleList = ArrayList<ArticleEntity>()
                for (response in data) {
                    if (response.articleId == articleId) {
                        val article = ArticleEntity(
                            response.articleId,
                            response.title,
                            response.description,
                            response.imagePath,
                            response.posted)

                        articleList.add(article)
                    }
                }
                localDataSource.getDetailArticle(articleId)
            }
        }.asLiveData()
    }

    override fun getAllHistory(): LiveData<Resource<PagedList<HistoryEntity>>> {
        return object : NetworkBoundResource<PagedList<HistoryEntity>, List<HistoryResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<HistoryEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllHistory(), config).build()
            }

            override fun shouldFetch(data: PagedList<HistoryEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<HistoryResponse>>> =
                remoteDataSource.getAllHistory()

            public override fun saveCallResult(data: List<HistoryResponse>) {
                val historyList = ArrayList<HistoryEntity>()
                for (response in data) {
                    val history = HistoryEntity(response.historyId,
                    response.disease,
                    response.posted)
                    historyList.add(history)
                }

                localDataSource.insertHistory(historyList)
            }
        }.asLiveData()
    }
}