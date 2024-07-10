package com.example.githubrepo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val avatar_url: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val repos_url: String,
    val score: Double,
    val type: String,
    val url: String,
    var bio: String?,
    var location: String?,
    val followers: Int?,
    val following: Int?,
    var name: String?,
    val blog: String?,
    var email: String?,
    val public_repos: Int?,
    var repositories: List<RepositoryModel>? = null
): Parcelable