package com.chinmay.testappmvvm.network

import com.chinmay.testappmvvm.model.Tests
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TestApi {

    @GET("{extension}")
    fun getTests(@Path("extension") type: String): Call<ArrayList<Tests>>
}