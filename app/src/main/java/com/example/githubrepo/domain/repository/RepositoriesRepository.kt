package com.example.githubrepo.domain.repository

import androidx.paging.PagingData
import com.example.githubrepo.domain.model.RepositoryModel
import kotlinx.coroutines.flow.Flow

interface RepositoriesRepository {

    fun search(searchQuery: String): Flow<PagingData<RepositoryModel>>

}