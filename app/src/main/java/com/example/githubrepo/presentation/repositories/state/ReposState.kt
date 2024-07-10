package com.example.githubrepo.presentation.repositories.state

import androidx.paging.PagingData
import com.example.githubrepo.domain.model.RepositoryModel
import com.example.githubrepo.domain.model.User
import kotlinx.coroutines.flow.Flow

data class ReposState(
    val searchQuery: String = "",
    val repositories: Flow<PagingData<RepositoryModel>>? = null
)