package com.example.githubrepo.domain.usecases.users

import com.example.githubrepo.domain.model.RepositoryModel
import com.example.githubrepo.domain.repository.UserRepository
import javax.inject.Inject

class GetUserRepos @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String): List<RepositoryModel>{
        return userRepository.getUserRepositories(username = username)
    }

}