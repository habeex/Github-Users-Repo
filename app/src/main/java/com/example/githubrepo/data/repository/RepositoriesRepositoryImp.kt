package com.example.githubrepo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubrepo.data.remote.RepositoriesApi
import com.example.githubrepo.data.remote.datasource.RepositoriesPagingSource
import com.example.githubrepo.domain.model.RepositoryModel
import com.example.githubrepo.domain.repository.RepositoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoriesRepositoryImp @Inject constructor(
    private val api: RepositoriesApi

    ) : RepositoriesRepository {
    override fun search(searchQuery: String): Flow<PagingData<RepositoryModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                RepositoriesPagingSource(
                    api = api,
                    searchQuery = searchQuery,
                )
            }
        ).flow
    }

}