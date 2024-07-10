package com.example.githubrepo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryModel(
    val full_name: String,
    val name: String?,
    val stargazers_count: Int,
    val visibility: String?,
    val updated_at: String?,
    val language: String?,
    val description: String?,
    val watchers: Int,
    val forks: Int,
    val owner: User?,
    val parent: RepositoryParentModel? = null,
    val topics: List<String>?
): Parcelable

@Parcelize
data class RepositoryParentModel(
    val full_name: String,
    val name: String,
    val owner: User
): Parcelable
