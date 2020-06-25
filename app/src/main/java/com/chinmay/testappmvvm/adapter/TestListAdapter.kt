package com.chinmay.testapp.adapter


import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.chinmay.testappmvvm.adapter.ListViewHolder
import com.chinmay.testappmvvm.databinding.ListitemCardLayoutBinding
import com.chinmay.testappmvvm.model.Tests
import com.chinmay.testappmvvm.viewmodels.TestListViewModel
import kotlin.collections.ArrayList


class TestListAdapter(private val testListViewModel: TestListViewModel): RecyclerView.Adapter<ListViewHolder>() {

     var testList = ArrayList<Tests>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ListitemCardLayoutBinding.inflate(inflater, parent, false)
        return ListViewHolder(dataBinding, testListViewModel)
    }

    override fun getItemCount(): Int {
        return testList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val test = testList[position]
        holder.bindTestDetails(test)

    }


    fun updateTestList(list: ArrayList<Tests>){
        this.testList = list
        notifyDataSetChanged()
    }

}