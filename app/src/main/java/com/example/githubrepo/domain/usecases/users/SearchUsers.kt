package com.example.githubrepo.domain.usecases.users

import androidx.paging.PagingData
import com.example.githubrepo.domain.model.User
import com.example.githubrepo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUsers @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<User>> {
        return userRepository.searchUsers(
            searchQuery = searchQuery,
        )
    }
}