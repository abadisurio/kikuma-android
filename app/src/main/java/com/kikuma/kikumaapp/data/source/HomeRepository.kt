package com.kikuma.kikumaapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kikuma.kikumaapp.data.NetworkBoundResource
import com.kikuma.kikumaapp.data.source.local.LocalDataSource
import com.kikuma.kikumaapp.data.source.local.entity.*
import com.kikuma.kikumaapp.data.source.remote.ApiResponse
import com.kikuma.kikumaapp.data.source.remote.RemoteDataSource
import com.kikuma.kikumaapp.data.source.remote.response.*
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
                    response.imageData,
                    response.posted)
                    historyList.add(history)
                }

                localDataSource.insertHistory(historyList)
            }
        }.asLiveData()
    }

    override fun getAllResult(): LiveData<Resource<List<DiseaseEntity>>> {
        return object : NetworkBoundResource<List<DiseaseEntity>, List<DiseaseResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<DiseaseEntity>> =
                    localDataSource.getAllResult()

            override fun shouldFetch(data: List<DiseaseEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<DiseaseResponse>>> =
                    remoteDataSource.getAllResult()

            public override fun saveCallResult(diseaseResponse: List<DiseaseResponse>) {
                val diseaseList = ArrayList<DiseaseEntity>()
                for (response in diseaseResponse) {
                    val disease = DiseaseEntity(response.resultId,
                            response.disease,
                            response.description,
                            response.percentage)
                    diseaseList.add(disease)
                }

                localDataSource.insertResult(diseaseList)
            }
        }.asLiveData()
    }

    override fun getDetailResult(resultId: String): LiveData<Resource<DiseaseEntity>> {
        return object : NetworkBoundResource<DiseaseEntity, List<DiseaseResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<DiseaseEntity> =
                    localDataSource.getDetailResult(resultId)

            override fun shouldFetch(diseaseEntity: DiseaseEntity?): Boolean =
                    diseaseEntity == null

            override fun createCall(): LiveData<ApiResponse<List<DiseaseResponse>>> =
                    remoteDataSource.getDetailResult(resultId)

            override fun saveCallResult(diseaseResponse: List<DiseaseResponse>) {
                val diseaseList = ArrayList<DiseaseEntity>()
                for (response in diseaseResponse) {
                    val disease = DiseaseEntity(response.resultId,
                            response.disease,
                            response.description,
                            response.percentage)
                    diseaseList.add(disease)
                }
                localDataSource.insertResult(diseaseList)
            }
        }.asLiveData()
    }

    override fun getTipsForDisease(forDisease: String): LiveData<Resource<List<TipsEntity>>> {
        return object : NetworkBoundResource<List<TipsEntity>, List<TipsResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TipsEntity>> =
                    localDataSource.getTipsForDisease(forDisease)

            override fun shouldFetch(data: List<TipsEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TipsResponse>>> =
                    remoteDataSource.getTipsForDisease(forDisease)

            public override fun saveCallResult(tipsResponse: List<TipsResponse>) {
                val tipsList = ArrayList<TipsEntity>()
                for (response in tipsResponse) {
                    val tips = TipsEntity(response.tipsId,
                            response.resultId,
                            response.tips,
                            response.forDisease)
                    tipsList.add(tips)
                }

                localDataSource.insertTips(tipsList)
            }
        }.asLiveData()
    }
    override fun getAllHospital(): LiveData<Resource<List<HospitalEntity>>> {
        return object : NetworkBoundResource<List<HospitalEntity>, List<HospitalResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<HospitalEntity>> =
                    localDataSource.getAllHospitals()

            override fun shouldFetch(data: List<HospitalEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<HospitalResponse>>> =
                    remoteDataSource.getAllHospital()

            public override fun saveCallResult(hospitalResponse: List<HospitalResponse>) {
                val hospitalList = ArrayList<HospitalEntity>()
                for (response in hospitalResponse) {
                    val hospital = HospitalEntity(response.hospitalId,
                            response.hospital,
                            response.address,
                            response.rate,
                            response.price,
                            response.imagePath)
                    hospitalList.add(hospital)
                }

                localDataSource.insertHospital(hospitalList)
            }
        }.asLiveData()
    }
/*
    override fun getResultWithTips(resultId: String): LiveData<DiseaseEntity> {
        //ini belum bener, soalnya kalo kaya gini result yg tampil selalu id yg terakhir
        val diseaseResult = MutableLiveData<DiseaseEntity>()
        remoteDataSource.getAllResult(object : RemoteDataSource.LoadAllResultCallback {
            override fun onAllResultReceived(diseaseResponse: List<DiseaseResponse>) {
                lateinit var disease: DiseaseEntity
                for (response in diseaseResponse) {
                    disease = DiseaseEntity(response.resultId,
                            response.disease,
                            response.description,
                            response.percentage)
                }
                diseaseResult.postValue(disease)
            }
        })
        return diseaseResult
        //Harusnya gini, tp entah knp selalu disease has not been initialized
        /*
        val diseaseResult = MutableLiveData<DiseaseEntity>()
        remoteDataSource.getAllResult(object : RemoteDataSource.LoadAllResultCallback {
            override fun onAllResultReceived(diseaseResponse: List<DiseaseResponse>) {
                lateinit var disease: DiseaseEntity
                for (response in diseaseResponse) {
                    if (response.resultId == resultId) {
                        disease = DiseaseEntity(response.resultId,
                                response.disease,
                                response.description,
                                response.percentage)
                    }
                }
                diseaseResult.postValue(disease)
            }
        })
        return diseaseResult
         */
    }
 */
/*
    override fun getAllTipsByResult(resultId: String): LiveData<Resource<List<TipsEntity>>> {
        return object : NetworkBoundResource<List<TipsEntity>, List<TipsResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TipsEntity>> =
                    localDataSource.getAllTips()
            override fun shouldFetch(data: List<TipsEntity>?): Boolean =
                    data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<TipsResponse>>> =
                    remoteDataSource.getTips(resultId)
            public override fun saveCallResult(tipsResponse: List<TipsResponse>) {
                val tipsList = ArrayList<TipsEntity>()
                for (response in tipsResponse) {
                    val tips = TipsEntity(response.tipsId,
                            response.resultId,
                            response.tips)
                    tipsList.add(tips)
                }
                localDataSource.insertTips(tipsList)
            }
        }.asLiveData()
    }
 */
}