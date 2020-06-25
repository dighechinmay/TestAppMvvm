package com.chinmay.testappmvvm.Repository

import com.chinmay.testappmvvm.model.Tests
import com.chinmay.testappmvvm.network.RetrofitServiceGenerator
import okhttp3.internal.Internal.instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestRepository {

    fun getTestList(type: String,onResult: (isSuccess: Boolean, response: ArrayList<Tests>?) -> Unit) {

        RetrofitServiceGenerator.instance.getTests(type).enqueue(object : Callback<ArrayList<Tests>> {
            override fun onResponse(call: Call<ArrayList<Tests>>?, response: Response<ArrayList<Tests>>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
           }

            override fun onFailure(call: Call<ArrayList<Tests>>?, t: Throwable?) {
                onResult(false, null)
            }

       })



    }

    companion object {
        private var INSTANCE: TestRepository? = null
        fun getInstance() = INSTANCE
            ?: TestRepository().also {
                INSTANCE = it
            }
    }
}