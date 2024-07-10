package com.example.githubrepo.users.viewmodel

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.githubrepo.domain.model.MockData
import com.example.githubrepo.domain.usecases.users.SearchUsers
import com.example.githubrepo.presentation.users.state.UsersEvent
import com.example.githubrepo.presentation.users.viewmodel.UsersViewModel
import com.example.githubrepo.utils.collectData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModelTest {

    private lateinit var searchUsersUseCase: SearchUsers
    private lateinit var usersViewModel: UsersViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        searchUsersUseCase = mockk()
        usersViewModel = UsersViewModel(searchUsersUseCase)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `UpdateSearchQuery event updates search query in state`() {
        // Given
        val searchQuery = "john"

        // When
        usersViewModel.onEvent(UsersEvent.UpdateSearchQuery(searchQuery))

        // Then
        assertEquals(usersViewModel.state.value.searchQuery, searchQuery)
    }

    @Test
    fun `SearchUsers event updates users in state`() = runTest(testDispatcher) {
        // Given
        val searchQuery = "habeex"
        val pagingData = PagingData.from(listOf(MockData.getUser()))
        every { searchUsersUseCase.invoke(searchQuery) } returns flowOf(pagingData)

        usersViewModel.onEvent(UsersEvent.UpdateSearchQuery(searchQuery))

        // When
        usersViewModel.onEvent(UsersEvent.SearchUsers)

        val usersFlow = usersViewModel.state.value.users
        val result = usersFlow?.first()?.collectData()

        // Then
        assertEquals(result, pagingData.collectData())
        verify { searchUsersUseCase.invoke(searchQuery) }
    }
}
