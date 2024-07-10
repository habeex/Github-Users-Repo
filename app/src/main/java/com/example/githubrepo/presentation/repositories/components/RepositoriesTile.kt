package com.example.githubrepo.presentation.repositories.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubrepo.R
import com.example.githubrepo.domain.model.RepositoryModel
import com.example.githubrepo.domain.model.User
import com.example.githubrepo.presentation.Dimens.Padding12
import com.example.githubrepo.presentation.Dimens.Padding16
import com.example.githubrepo.presentation.Dimens.Padding2
import com.example.githubrepo.presentation.Dimens.Padding4
import com.example.githubrepo.presentation.Dimens.Padding8
import com.example.githubrepo.util.LanguageColor
import com.example.githubrepo.util.TimeUtils
import com.example.githubrepo.util.Utils

@Composable
fun RepositoryTile(
    modifier: Modifier = Modifier, repository: RepositoryModel, onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier.clickable { onClick?.invoke() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.0.dp,
            draggedElevation = 0.0.dp,
            focusedElevation = 0.0.dp,
            disabledElevation = 0.0.dp,
        ),
        border = BorderStroke(0.4.dp, colorResource(id = R.color.stroke_color))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding16),
        ) {
            Row(
                verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f), verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = colorResource(id = R.color.purple),
                                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("${repository.owner?.login}/")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("${repository.name}")
                            }
                        })
                    }
                    Spacer(modifier = Modifier.width(Padding8))
                    Box(
                        modifier = Modifier
                            .border(
                                .4.dp,
                                colorResource(id = R.color.stroke_color),
                                shape = CircleShape,
                            )
                            .background(Color.Transparent)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            text = "${repository.visibility}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 10.sp,
                        )
                    }
                }
                Spacer(modifier = Modifier.width(Padding16))
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(Padding2))
                        Text(
                            text = Utils.formatNumber(repository.stargazers_count),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    if (!repository.language.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.width(Padding4))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(colorResource(id = LanguageColor.getColor(repository.language)))
                            )
                            Spacer(modifier = Modifier.width(Padding2))
                            Text(
                                text = repository.language,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(Padding12))
            Text(
                text = repository.description ?: "",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.W500
            )
            repository.topics?.let { topics ->
                Spacer(modifier = Modifier.height(Padding12))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    items(topics.size){
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .padding(end = Padding8)
                                .background(colorResource(id = R.color.lighter_blue))
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                text = topics[it],
                                color = colorResource(id = R.color.deep_blue),
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.W600,
                                fontSize = 10.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RepositoryTilePreview() {
    RepositoryTile(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.secondary),
        RepositoryModel(
            owner = User(
                login = "habeex",
                id = 1849920,
                avatar_url = "https://avatars.githubusercontent.com/u/1849920?v=4",
                url = "https://api.github.com/users/habeex",
                html_url = "https://github.com/habeex",
                repos_url = "https://api.github.com/users/habeex/repos",
                type = "User",
                score = 1.0,
                bio = "",
                location = "",
                followers = 0,
                following = 0,
                name = "",
                blog = "",
                public_repos = 0,
                email = ""
            ),
            name = "BluetoothPrinter",
            full_name = "habeex/BluetoothPrinter",
            stargazers_count = 105485,
            forks = 10238,
            watchers = 105485,
            visibility = "public",
            language = "C",
            updated_at = "2024-06-08T09:54:25Z",
            description = "Empowering everyone to build reliable and efficient software.",
            topics = listOf("Design system", "Component- misc", "Status- New")
        ),
        onClick = {})
}