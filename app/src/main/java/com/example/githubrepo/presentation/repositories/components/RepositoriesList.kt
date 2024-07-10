package com.example.githubrepo.presentation.repositories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.githubrepo.R
import com.example.githubrepo.domain.model.RepositoryModel
import com.example.githubrepo.presentation.Dimens.Padding8
import com.example.githubrepo.presentation.common.EmptyScreen


@Composable
fun RepositoriesList(
    modifier: Modifier = Modifier,
    repositories: LazyPagingItems<RepositoryModel>,
    onClick: (RepositoryModel) -> Unit
) {
    val handlePagingResult = handlePagingResult(repositories)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Padding8),
        ) {
            items(
                count = repositories.itemCount,
            ) {
                repositories[it]?.let { repository ->
                    RepositoryTile(
                        modifier = Modifier
                            .fillMaxWidth(),
                        repository, onClick = { onClick(repository) }
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(repositories: LazyPagingItems<RepositoryModel>): Boolean {
    val loadState = repositories.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error, iconId = R.drawable.empty_record_state)
            false
        }

        repositories.itemCount == 0 -> {
            EmptyScreen(message = "We’ve searched the ends of the earth, repository not found, please try again", iconId = R.drawable.empty_record_state)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Padding8)) {
        repeat(20) {
            RepositoryTileShimmerEffect()
        }
    }
}