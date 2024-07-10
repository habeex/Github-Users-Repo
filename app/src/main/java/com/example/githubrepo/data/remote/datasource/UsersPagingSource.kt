package com.example.githubrepo.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubrepo.data.remote.UserApi
import com.example.githubrepo.domain.model.User
import retrofit2.HttpException

class SearchUsersPagingSource(
    private val api: UserApi,
    private val searchQuery: String,
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalUsersCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1
        return try {
            val userResponse = api.searchUsers(searchQuery = searchQuery, page = page)
            totalUsersCount += userResponse.items.size
            val users = userResponse.items.distinctBy { it.login } //Remove duplicates

            LoadResult.Page(
                data = users,
                nextKey = if (totalUsersCount == userResponse.total_count) null else page + 1,
                prevKey = null
            )
        } catch (e: HttpException){
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }


}