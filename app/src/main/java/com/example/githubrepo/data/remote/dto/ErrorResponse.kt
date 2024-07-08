package com.example.githubrepo.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code") var code: String? = null,
    @SerializedName("message") var message: String? = null
)