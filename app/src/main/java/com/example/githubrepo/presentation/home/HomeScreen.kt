package com.example.githubrepo.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubrepo.R
import com.example.githubrepo.presentation.Dimens.Padding16
import com.example.githubrepo.presentation.Dimens.Padding20
import com.example.githubrepo.presentation.Dimens.Padding24
import com.example.githubrepo.presentation.navgraph.Route
import com.example.githubrepo.ui.theme.GithubRepoTheme


@Composable
fun HomeScreen(
    onNavigate: (route: String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Padding20)
            .statusBarsPadding()
    ) {
        Text(
            text = "Home",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(Padding24))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Padding16),
        ) {
            HomeCardItem(
                modifier = Modifier.weight(1f),
                icon = R.drawable.ic_user_rounded,
                backgroundColor = colorResource(id = R.color.light_blue),
                text = "Users",
                onClick = { onNavigate(Route.UsersScreen.route) }
            )
            HomeCardItem(
                modifier = Modifier.weight(1f),
                icon = R.drawable.ic_wallet_rounded,
                backgroundColor = colorResource(id = R.color.light_purple),
                text = "Repositories",
                onClick = {onNavigate(Route.RepositoryScreen.route)}
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    GithubRepoTheme(dynamicColor = false) {
        HomeScreen(
            onNavigate = {}
        )
    }
}