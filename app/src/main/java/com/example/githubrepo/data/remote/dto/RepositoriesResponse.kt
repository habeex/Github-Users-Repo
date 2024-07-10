package com.example.githubrepo.data.remote.dto

import com.example.githubrepo.domain.model.RepositoryModel

data class RepositoriesResponse(
    val incomplete_results: Boolean,
    val items: List<RepositoryModel>,
    val total_count: Int
)