package com.example.githubrepo.domain.usecases.users

import com.example.githubrepo.domain.model.User
import com.example.githubrepo.domain.repository.UserRepository
import javax.inject.Inject

class GetUser @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(username: String): User{
        return userRepository.getUser(username = username)
    }

}