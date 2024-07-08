package com.example.githubrepo.util

import androidx.multidex.BuildConfig
import com.google.gson.Gson

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
}

fun Throwable.printErrorDebugOnly(){
    if(BuildConfig.DEBUG)
        printStackTrace()
}
