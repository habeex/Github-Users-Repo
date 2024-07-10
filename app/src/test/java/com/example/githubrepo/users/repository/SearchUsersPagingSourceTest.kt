package com.example.githubrepo.users.repository

import androidx.paging.PagingSource
import com.example.githubrepo.data.remote.UserApi
import com.example.githubrepo.data.remote.datasource.SearchUsersPagingSource
import com.example.githubrepo.data.remote.dto.UserResponse
import com.example.githubrepo.data.remote.error.ErrorHandler
import com.example.githubrepo.domain.model.MockData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class SearchUsersPagingSourceTest {

    private val api = mockk<UserApi>()
    private val searchQuery = "habeex"
    private val pagingSource = SearchUsersPagingSource(api, searchQuery)

    @Test
    fun `search users returns  successful response`() = runTest {
        // Given
        val users = listOf(MockData.getUser())
        val userResponse = UserResponse(total_count = 1, items = users, incomplete_results = false)

        coEvery { api.searchUsers(searchQuery, 1) } returns userResponse

        // When
        val loadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = 1,
            placeholdersEnabled = false
        )
        val result = pagingSource.load(loadParams)

        // Then
        assertTrue(result is PagingSource.LoadResult.Page)
        result as PagingSource.LoadResult.Page
        assertEquals(users, result.data)
        assertEquals(null, result.prevKey)
        assertEquals(null, result.nextKey)
    }

    @Test
    fun `search users returns error on HTTP exception`() = runTest {
        // Given
        val exception = HttpException(Response.error<Any>(404, ResponseBody.create(null, "")))

        coEvery { api.searchUsers(searchQuery, 1) } throws exception

        // When
        val loadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = 1,
            placeholdersEnabled = false
        )
        val result = pagingSource.load(loadParams)

        // Then
        assertTrue(result is PagingSource.LoadResult.Error)
        result as PagingSource.LoadResult.Error
        assertEquals(exception, result.throwable)
        val error = ErrorHandler.handleError(result.throwable)
        assertEquals("No record found", error)
    }

    @Test
    fun `returns unknown error on generic exception`() = runTest {
        val message = "An unknown error occurred trying to process this request. Please try again later"
        // Given
        val exception = Exception(message)

        coEvery { api.searchUsers(searchQuery, 1) } throws exception

        // When
        val loadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = 1,
            placeholdersEnabled = false
        )
        val result = pagingSource.load(loadParams)

        // Then
        assertTrue(result is PagingSource.LoadResult.Error)
        result as PagingSource.LoadResult.Error
        assertEquals(exception, result.throwable)

        val error = ErrorHandler.handleError(result.throwable)
        assertEquals(message, error)
    }
}