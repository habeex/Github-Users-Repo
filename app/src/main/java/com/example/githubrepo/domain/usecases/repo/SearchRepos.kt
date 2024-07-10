package com.example.githubrepo.domain.usecases.repo

import androidx.paging.PagingData
import com.example.githubrepo.domain.model.RepositoryModel
import com.example.githubrepo.domain.repository.RepositoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepos @Inject constructor(
    private val repository: RepositoriesRepository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<RepositoryModel>> {
        return repository.search(
            searchQuery = searchQuery,
        )
    }
}