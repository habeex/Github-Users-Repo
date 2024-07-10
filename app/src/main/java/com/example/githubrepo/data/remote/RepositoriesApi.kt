package com.example.githubrepo.data.remote

import com.example.githubrepo.data.remote.dto.RepositoriesResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesApi {

    @GET("search/repositories")
    suspend fun search(
        @Query("q") searchQuery: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): RepositoriesResponse
}