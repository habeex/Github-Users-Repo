package com.example.githubrepo.presentation.users.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.githubrepo.R
import com.example.githubrepo.domain.model.User
import com.example.githubrepo.presentation.Dimens.Padding8
import com.example.githubrepo.presentation.common.EmptyScreen


@Composable
fun UsersList(
    modifier: Modifier = Modifier,
    users: LazyPagingItems<User>,
    onClick: (User) -> Unit
) {
    val handlePagingResult = handlePagingResult(users)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Padding8),
        ) {
            items(
                count = users.itemCount,
            ) {
                users[it]?.let { user ->
                    //Note: Github API does not return full information on search endpoint
                    // This is to simulate the data showing on the UI
                    if (it % 2 == 0) {
                        user.name = "Olorunishola Habeeb"
                        user.location = "Abuja, Nigeria"
                        user.email = "test@gmail.com"
                        user.bio = "This is a random bio, which will be replace with actual content"
                    }
                    UserTile(user = user, onClick = { onClick(user) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(users: LazyPagingItems<User>): Boolean {
    val loadState = users.loadState
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

        users.itemCount == 0 -> {
            EmptyScreen(
                message = "We’ve searched the ends of the earth and we’ve not found this user, please try again",
                iconId = R.drawable.empty_record_state
            )
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
            UserCardShimmerEffect()
        }
    }
}