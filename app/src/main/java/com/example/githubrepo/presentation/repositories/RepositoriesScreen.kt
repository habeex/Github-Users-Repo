package com.example.githubrepo.presentation.repositories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubrepo.R
import com.example.githubrepo.presentation.Dimens.Padding16
import com.example.githubrepo.presentation.Dimens.Padding20
import com.example.githubrepo.presentation.Dimens.Padding8
import com.example.githubrepo.presentation.common.EmptyScreen
import com.example.githubrepo.presentation.common.SearchBar
import com.example.githubrepo.presentation.repositories.components.RepositoriesList
import com.example.githubrepo.presentation.repositories.state.ReposEvent
import com.example.githubrepo.presentation.repositories.state.ReposState

@Composable
fun RepositoriesScreen(
    state: ReposState,
    event:(ReposEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Padding20)
            .statusBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(Padding16))
        Text(
            text = "Repositories",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(Padding8))
        SearchBar(
            text = state.searchQuery,
            hint = "Search for repositories...",
            readOnly = false,
            onValueChange = { event(ReposEvent.UpdateSearchQuery(it)) },
            onSearch = { event(ReposEvent.SearchRepos) }
        )
        Spacer(modifier = Modifier
            .height(Padding16)
            .background(Color.Transparent))
        if(state.repositories == null)
            EmptyScreen(message = "Search Github for repositories, issues and pull requests!")

        state.repositories?.let {
            val repositories = it.collectAsLazyPagingItems()
            RepositoriesList(repositories = repositories ) {

            }
        }

    }
}