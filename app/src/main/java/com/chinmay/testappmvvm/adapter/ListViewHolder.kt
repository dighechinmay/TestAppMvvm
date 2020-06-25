package com.chinmay.testappmvvm.adapter

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chinmay.testappmvvm.BR
import com.chinmay.testappmvvm.model.Tests
import com.chinmay.testappmvvm.viewmodels.TestListViewModel
import kotlinx.android.synthetic.main.listitem_card_layout.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ListViewHolder constructor(private val dataBinding: ViewDataBinding, private val testListViewModel: TestListViewModel)
    : RecyclerView.ViewHolder(dataBinding.root){

   // private var view: View = v

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindTestDetails(test: Tests){

        dataBinding.setVariable(BR.itemData,test)
        dataBinding.executePendingBindings()


//        itemView.test_heading.text = test.title
//        itemView.section_textView.text = test.standard+"th "+test.section
//        itemView.createDate_textView.text = formatTime(test.createdDate)
//        itemView.eventDate_textView.text = "On "+ formatDate(test.eventDate)


    }



}