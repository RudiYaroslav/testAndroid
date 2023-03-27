package com.example.testaiwe.data.api

import io.reactivex.Single
import retrofit2.http.GET



interface InfoApi {

    @GET("global")
    fun getDataInfo():Single<ResponseData>
}