package com.example.githubrepo.users.usecase

import com.example.githubrepo.domain.model.MockData
import com.example.githubrepo.domain.model.User
import com.example.githubrepo.domain.repository.UserRepository
import com.example.githubrepo.domain.usecases.users.GetUser
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetUserTest {

    private lateinit var userRepository: UserRepository
    private lateinit var getUser: GetUser

    @Before
    fun setup() {
        userRepository = mockk()
        getUser = GetUser(userRepository)
    }

    @Test
    fun `invoke calls userRepository getUser`() = runBlockingTest {
        // Given
        val username = "habeex"
        val user = MockData.getUser()
        coEvery { userRepository.getUser(username) } returns user

        // When
        val result: User = getUser(username)

        // Then
        assertEquals(result, user)
        assertEquals(result.login, username)
    }
}