package com.example.githubrepo.presentation.users.state

sealed class UsersEvent {

    data class UpdateSearchQuery(val searchQuery: String) : UsersEvent()

    object SearchUsers : UsersEvent()
}