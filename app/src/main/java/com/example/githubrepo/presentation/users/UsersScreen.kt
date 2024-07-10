package com.example.githubrepo.presentation.users

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
            .padding(horizontal = Padding20)
            .statusBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(Padding16))
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
        Spacer(modifier = Modifier.height(Padding16).background(Color.Transparent))
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