package com.example.githubrepo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubrepo.data.remote.UserApi
import com.example.githubrepo.data.remote.datasource.SearchUsersPagingSource
import com.example.githubrepo.domain.model.User
import com.example.githubrepo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl@Inject constructor(
    private val api: UserApi,
) : UserRepository {
    override fun searchUsers(searchQuery: String): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchUsersPagingSource(
                    api = api,
                    searchQuery = searchQuery,
                )
            }
        ).flow
    }

    override suspend fun getUser(username: String): User {
        return  api.getUser(username)
    }
}