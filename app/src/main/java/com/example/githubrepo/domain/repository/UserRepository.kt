package com.example.githubrepo.domain.repository

import androidx.paging.PagingData
import com.example.githubrepo.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun searchUsers(searchQuery: String): Flow<PagingData<User>>

    suspend fun getUser(username: String): User

}