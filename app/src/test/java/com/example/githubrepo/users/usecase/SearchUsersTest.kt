package com.example.githubrepo.users.usecase

import androidx.paging.PagingData
import com.example.githubrepo.domain.model.User
import com.example.githubrepo.domain.repository.UserRepository
import com.example.githubrepo.domain.usecases.users.SearchUsers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchUsersTest {

    private lateinit var userRepository: UserRepository
    private lateinit var searchUsers: SearchUsers

    @Before
    fun setup() {
        userRepository = mockk()
        searchUsers = SearchUsers(userRepository)
    }

    @Test
    fun `invoke calls userRepository searchUsers`() = runBlockingTest {
        // Given
        val searchQuery = "habeex"
        val pagingData: PagingData<User> = PagingData.empty()
        coEvery { userRepository.searchUsers(searchQuery) } returns flowOf(pagingData)

        // When
        val result: Flow<PagingData<User>> = searchUsers(searchQuery)

        // Then
        result.collectLatest { collectedPagingData ->
            assertEquals(collectedPagingData, pagingData)
        }
    }
}