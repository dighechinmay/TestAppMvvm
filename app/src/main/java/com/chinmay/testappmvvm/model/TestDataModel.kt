package com.chinmay.testappmvvm.model

import android.os.Parcel
import android.os.Parcelable

data class Tests(

        val title: String,
        val descrition: String,
        val eventDate: Long,
        val createdDate: Long,
        val standard: String,
        val section: String
)
