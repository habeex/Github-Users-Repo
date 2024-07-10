package com.example.githubrepo.util

import androidx.multidex.BuildConfig
import com.google.gson.Gson
import java.text.NumberFormat

object Utils {

    fun <T>genericClassJsonCast(value: String?, baseClass: Class<T>): T?{
        return if(value != null){
            try{
                val gson = Gson()
                gson.fromJson(value, baseClass)
            }catch (ex: Exception){
                ex.printErrorDebugOnly()
                null
            }

        }else
            null
    }

    fun formatNumber(number: Int): String {
        return if (number >= 1000) {
            NumberFormat.getNumberInstance().format(number / 1000) + "k"
        } else {
            number.toString()
        }
    }
}

fun Throwable.printErrorDebugOnly(){
    if(BuildConfig.DEBUG)
        printStackTrace()
}
