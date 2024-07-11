package com.example.githubrepo.presentation.userdetails

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubrepo.R
import com.example.githubrepo.domain.model.User
import com.example.githubrepo.presentation.Dimens.Padding12
import com.example.githubrepo.presentation.Dimens.Padding16
import com.example.githubrepo.presentation.Dimens.Padding20
import com.example.githubrepo.presentation.Dimens.Padding4
import com.example.githubrepo.presentation.Dimens.Padding8
import com.example.githubrepo.presentation.common.AppTopBar
import com.example.githubrepo.presentation.common.EmptyScreen
import com.example.githubrepo.presentation.common.ErrorComponent
import com.example.githubrepo.presentation.userdetails.components.UserRepoListShimmerEffect
import com.example.githubrepo.presentation.userdetails.components.UserRepoShimmerEffect
import com.example.githubrepo.presentation.userdetails.components.UserRepoTile
import com.example.githubrepo.util.DataState

@Composable
fun UserDetailsScreen(
    state: DataState<User>,
    navigateUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        AppTopBar(
            title = "Github User Details",
            onBackClick = navigateUp
        )
        when (state) {
            is DataState.Loading -> {
                if (state.isLoading) {
                    UserRepoShimmerEffect()
                }
            }

            is DataState.Response -> {
                ErrorComponent.Show(
                    uiComponent = state.uiComponent,
                    onClick = { navigateUp() }
                )
            }

            is DataState.Success -> {
                val user = state.data
                UserDetails(user = user)
            }

            is DataState.Update -> {
                val user = state.data
                UserDetails(user = user, true)
            }
        }

    }
}

@Composable
fun UserDetails(user: User, isUpdated: Boolean = false) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(horizontal = Padding20)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(context).data(user.avatar_url).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(Padding8))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = user.login,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold

                )
                Spacer(modifier = Modifier.height(Padding4))
                Text(
                    text = user.name ?: "",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        Spacer(modifier = Modifier.height(Padding16))
        user.bio?.let {
            Text(
                text = "${user.bio}".trim(),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(Padding16))
        }
        if (user.location?.isNotEmpty() == true || user.blog?.isNotEmpty() == true) {
            Row {
                user.location?.let {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null,
                        tint = colorResource(id = R.color.grey)
                    )
                    Spacer(modifier = Modifier.width(Padding4))
                    Text(
                        text = user.location.toString(),
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.grey),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.width(Padding8))
                }
                user.blog?.let {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_link),
                        contentDescription = null,
                        tint = colorResource(id = R.color.grey)

                    )
                    Spacer(modifier = Modifier.width(Padding4))
                    Box(
                        modifier = Modifier.clickable {
                            Intent(Intent.ACTION_VIEW).also {
                                val link =
                                    if (user.blog.startsWith("https:")) user.blog else "https://${user.blog}"
                                it.data = Uri.parse(link)
                                if (it.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(it)
                                }
                            }
                        }
                    ) {
                        Text(
                            text = user.blog,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.W600,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(Padding12))
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_people),
                contentDescription = null,
                tint = colorResource(id = R.color.grey)

            )
            Spacer(modifier = Modifier.width(Padding4))
            Text(
                text = "${user.followers} followers  .  ${user.following} following",
                fontSize = 12.sp,
                color = colorResource(id = R.color.grey),
                style = MaterialTheme.typography.bodySmall,
            )

        }
        Spacer(modifier = Modifier.height(Padding16))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Repositories",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.W600,
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(Padding4))
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.lighter_grey))
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                    text = "${user.public_repos}",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.W600,
                    fontSize = 10.sp,
                )
            }
        }
        Spacer(modifier = Modifier.height(Padding8))
        Row {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp
            )
            HorizontalDivider(
                modifier = Modifier.weight(1.85f),
                color = colorResource(id = R.color.lighter_grey),
                thickness = 2.dp
            )
        }
        if (user.repositories.isNullOrEmpty() && !isUpdated)
            UserRepoListShimmerEffect()
        if (user.repositories.isNullOrEmpty() && isUpdated)
            EmptyScreen("No repositories found", iconId = R.drawable.empty_record_state)
        user.repositories?.let { repositories ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = Padding16),
                verticalArrangement = Arrangement.spacedBy(Padding8),
                contentPadding = PaddingValues(vertical = Padding8)
            ) {
                items(repositories.size) {
                    UserRepoTile(
                        modifier = Modifier
                            .fillMaxWidth(),
                        repositories[it], onClick = {}
                    )
                }
            }
        }

    }
}