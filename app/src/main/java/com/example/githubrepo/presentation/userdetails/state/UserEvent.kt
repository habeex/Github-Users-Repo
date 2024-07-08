package com.example.githubrepo.presentation.userdetails.state

import com.example.githubrepo.domain.model.User


sealed class UserEvent {
    data class GetUser(val user: User) : UserEvent()
}