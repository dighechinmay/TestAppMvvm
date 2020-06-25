package com.chinmay.testappmvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import com.chinmay.testappmvvm.Repository.TestRepository
import com.chinmay.testappmvvm.model.Tests

class TestListViewModel:BaseViewModel() {

    val testListLive = MutableLiveData<ArrayList<Tests>>()


    fun fetchTestList(){
        dataLoading.value = true
        TestRepository.getInstance().getTestList("school") { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                testListLive.value = response
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}
