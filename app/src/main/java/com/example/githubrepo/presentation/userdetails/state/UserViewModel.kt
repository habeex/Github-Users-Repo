package com.example.githubrepo.presentation.userdetails.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepo.data.remote.error.ErrorHandler
import com.example.githubrepo.domain.model.User
import com.example.githubrepo.domain.usecases.users.GetUser
import com.example.githubrepo.util.DataState
import com.example.githubrepo.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getSavedUserUseCase: GetUser,
) : ViewModel() {

    private val _state = MutableStateFlow<DataState<User>>(DataState.Loading(false))
    val state: StateFlow<DataState<User>> = _state

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.GetUser -> {
                getUser(event.user.login)
            }
        }
    }

    private fun getUser(username: String){
        viewModelScope.launch {
           try {
               _state.value = DataState.Loading(true)
               val user = getSavedUserUseCase(username = username)
               _state.value = DataState.Success(user)
           }catch (e: Exception){
               val message = ErrorHandler.handleError(e)
               _state.value = DataState.Response(UIComponent.Dialog(
                   title = "Failed",
                   message = message
               ), e)
           }
        }
    }

}