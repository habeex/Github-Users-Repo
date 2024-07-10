package com.example.githubrepo.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubrepo.data.remote.RepositoriesApi
import com.example.githubrepo.domain.model.RepositoryModel
import retrofit2.HttpException
import java.lang.Exception

class RepositoriesPagingSource(
    private val api: RepositoriesApi,
    private val searchQuery: String,
) : PagingSource<Int, RepositoryModel>() {

    override fun getRefreshKey(state: PagingState<Int, RepositoryModel>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalRepositoryModelsCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {
        val page = params.key ?: 1
        return try {
            val response = api.search(searchQuery = searchQuery, page = page, perPage = 20, order = "desc" )
            totalRepositoryModelsCount += response.items.size
            val repos = response.items.distinctBy { it.name } //Remove duplicates

            LoadResult.Page(
                data = repos,
                nextKey = if (totalRepositoryModelsCount == response.total_count) null else page + 1,
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