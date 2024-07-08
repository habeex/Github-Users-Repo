package com.example.githubrepo.data.remote.dto

import com.example.githubrepo.domain.model.User

data class UserResponse (
    val incomplete_results: Boolean,
    val items: List<User>,
    val total_count: Int
)