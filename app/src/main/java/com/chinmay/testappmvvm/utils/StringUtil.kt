package com.chinmay.testappmvvm.utils

object StringUtil {

    @JvmStatic
     fun formatStdString(standard:String,section:String):String{
        return "$standard th $section"
    }
}