package com.example.githubrepo.presentation.repositories.state

sealed class ReposEvent {

    data class UpdateSearchQuery(val searchQuery: String) : ReposEvent()

    object SearchRepos : ReposEvent()
}