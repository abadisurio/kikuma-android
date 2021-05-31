package com.kikuma.kikumaapp.Api

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @POST("predict")
    fun uploadImage(
            @Body json: RequestBody
    ): Call<RawApiResponse>
}