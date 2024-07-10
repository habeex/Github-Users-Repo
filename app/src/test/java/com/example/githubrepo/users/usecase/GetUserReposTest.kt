package com.example.githubrepo.users.usecase

import com.example.githubrepo.domain.model.MockData
import com.example.githubrepo.domain.model.RepositoryModel
import com.example.githubrepo.domain.repository.UserRepository
import com.example.githubrepo.domain.usecases.users.GetUserRepos
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetUserReposTest {

    private lateinit var userRepository: UserRepository
    private lateinit var getUserRepos: GetUserRepos

    @Before
    fun setup() {
        userRepository = mockk()
        getUserRepos = GetUserRepos(userRepository)
    }

    @Test
    fun `invoke calls userRepository getUserRepositories`() = runBlockingTest {
        // Given
        val username = "habeex"
        val repositories = listOf(
            MockData.getRepo()
        )
        coEvery { userRepository.getUserRepositories(username) } returns repositories

        // When
        val result: List<RepositoryModel> = getUserRepos(username)

        // Then validate
        assertTrue(result.size == repositories.size)
        assertEquals(result, repositories)
    }
}