package com.example.githubrepo.data.remote

import com.example.githubrepo.data.remote.dto.UserResponse
import com.example.githubrepo.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
    ): UserResponse

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String,
    ): User
}