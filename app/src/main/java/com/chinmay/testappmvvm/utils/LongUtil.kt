package com.chinmay.testappmvvm.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LongUtil {


    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    fun formatDate(date: Long):String{

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("d MMM")

        return current.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    fun formatTime(date: Long):String{

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("h:mm a")

        return current.format(formatter)
    }
}