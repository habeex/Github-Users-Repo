package com.example.githubrepo.presentation.users.state

import androidx.paging.PagingData
import com.example.githubrepo.domain.model.User
import kotlinx.coroutines.flow.Flow

data class UsersState(
    val searchQuery: String = "",
    val users: Flow<PagingData<User>>? = null
)