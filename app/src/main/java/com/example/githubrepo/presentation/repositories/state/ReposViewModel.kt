package com.example.githubrepo.presentation.repositories.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.githubrepo.domain.usecases.repo.SearchRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val searchReposUseCase: SearchRepos
) : ViewModel() {

    private var _state = mutableStateOf(ReposState())
    val state: State<ReposState> = _state


    fun onEvent(event: ReposEvent) {
        when (event) {
            is ReposEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is ReposEvent.SearchRepos -> {
                searchRepo()
            }
        }
    }

    private fun searchRepo() {
        val repositories = searchReposUseCase(
            searchQuery = _state.value.searchQuery,
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(repositories = repositories)
    }


}