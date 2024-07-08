package com.example.githubrepo.presentation.users.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.githubrepo.domain.usecases.users.SearchUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsers
) : ViewModel() {

    private var _state = mutableStateOf(UsersState())
    val state: State<UsersState> = _state


    fun onEvent(event: UsersEvent) {
        when (event) {
            is UsersEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is UsersEvent.SearchUsers -> {
                searchUser()
            }
        }
    }

    private fun searchUser() {
        val users = searchUsersUseCase(
            searchQuery = _state.value.searchQuery,
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(users = users)
    }


}