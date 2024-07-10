package com.example.githubrepo.data.remote.error

import com.example.githubrepo.data.remote.dto.ErrorResponse
import com.example.githubrepo.util.Utils
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHandler {

    private const val _500 = "Internal Server Error. Please try again later"
    private const val _401 = "Authorisation Failed: User not authorised"
    private const val _404 = "No record found"
    private const val _400 = "Client Error: Bad Request"
    private const val _502 = "Server Error: Bad Gateway"
    private const val _503 = "Server Error: Service Unavailable"
    private const val _504 = "Server Error: Gateway TimeOut"
    private const val exError = "Error occurred trying to access the server. Please try again later"
    private const val unknownError = "An unknown error occurred trying to process this request. Please try again later"
    private const val connectError = "It appears your internet connection is slow or not connected. " +
            "Please connect to a stable internet and try again"
    private const val timeoutError = "Connection Gateway TimeOut"


    private fun <T : Any?> handleResponseError(response: Response<T>?): String{
        try{
            when (response?.code()) {
                400 -> return _400
                401 -> return _401
                404 -> return _404
                in 402..499 -> return exError
                500 -> return _500
                502 -> return _502
                503 -> return _503
                504 -> return _504
                else -> {
                    val error: ErrorResponse? = Utils.genericClassJsonCast(response?.errorBody()?.string(), ErrorResponse::class.java)
                    return error?.message ?: unknownError
                }
            }
        }catch(ex:Exception){
            ex.printStackTrace()
            return exError
        }
    }

    fun handleError(t: Throwable?): String{
        t?.printStackTrace()
        return  when (t) {
            is SocketTimeoutException -> timeoutError
            is UnknownHostException, is ConnectException -> connectError
            is retrofit2.HttpException -> handleResponseError(t.response())
            else -> unknownError
        }
    }
}