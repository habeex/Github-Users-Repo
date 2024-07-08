package com.example.githubrepo.presentation.users

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
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubrepo.domain.model.User
import com.example.githubrepo.presentation.Dimens.Padding16
import com.example.githubrepo.presentation.Dimens.Padding20
import com.example.githubrepo.presentation.Dimens.Padding8
import com.example.githubrepo.presentation.common.EmptyScreen
import com.example.githubrepo.presentation.common.SearchBar
import com.example.githubrepo.presentation.users.components.UsersList
import com.example.githubrepo.presentation.users.state.UsersEvent
import com.example.githubrepo.presentation.users.state.UsersState

@Composable
fun UsersScreen(
    state: UsersState,
    event:(UsersEvent) -> Unit,
    navigateToDetails:(User) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Padding20)
            .statusBarsPadding()
    ) {
        Text(
            text = "Users",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(Padding8))
        SearchBar(
            text = state.searchQuery,
            hint = "Search for users...",
            readOnly = false,
            onValueChange = { event(UsersEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(UsersEvent.SearchUsers)
            }
        )
        Spacer(modifier = Modifier.height(Padding16))
        if(state.users == null)
            EmptyScreen(message = "Search Github for users...")

        state.users?.let {
            val users = it.collectAsLazyPagingItems()
            UsersList(
                users = users,
                onClick = navigateToDetails
            )
        }

    }
}