package com.example.githubrepo.users.repository

import com.example.githubrepo.data.remote.UserApi
import com.example.githubrepo.data.remote.dto.UserResponse
import com.example.githubrepo.data.repository.UserRepositoryImpl
import com.example.githubrepo.domain.model.MockData
import com.example.githubrepo.domain.repository.UserRepository
import com.example.githubrepo.utils.collectData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepositoryImplTest {

    private lateinit var api: UserApi
    private lateinit var repository: UserRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        api = mockk()
        repository = UserRepositoryImpl(api)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchUsers should returns PagingData size less than 2 on success`() = runBlocking {
        // Given
        val searchQuery = "habeex"
        val users = listOf(MockData.getUser())
        coEvery { api.searchUsers(searchQuery, 1) } returns UserResponse(
            total_count = 1,
            items = users,
            incomplete_results = false
        )

        // When
        val result = repository.searchUsers(searchQuery)
        val resultData = result.first().collectData()
        // Then
        assertTrue(resultData.size < 2)
    }

    @Test
    fun `getUser should returns User on success`() = runTest {
        // Given
        val username = "habeex"
        val user = MockData.getUser()
        coEvery { api.getUser(username) } returns user

        // When
        val result = repository.getUser(username)

        // Then
        assertEquals(result, user)
    }

    @Test
    fun `getUserRepositories should returns list of user repositories on success`() = runTest {
        // Given
        val username = "habeex"
        val repositories = listOf(
            MockData.getRepo()
        )
        coEvery { api.getUserRepositories(username) } returns repositories

        // When
        val result = repository.getUserRepositories(username)

        // Then
        assertEquals(result, repositories)
    }

}