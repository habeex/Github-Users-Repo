package com.example.githubrepo.presentation.users.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubrepo.R
import com.example.githubrepo.domain.model.User
import com.example.githubrepo.presentation.Dimens.Padding4
import com.example.githubrepo.presentation.Dimens.Padding6
import com.example.githubrepo.presentation.Dimens.Padding8

@Composable
fun UserTile(
    modifier: Modifier = Modifier,
    user: User,
    onClick: (() -> Unit)? = null
){
    val context = LocalContext.current
    Card(
        modifier = modifier.clickable { onClick?.invoke() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.0.dp,
            draggedElevation = 0.0.dp,
            focusedElevation = 0.0.dp,
            disabledElevation = 0.0.dp,
        ),
        border = BorderStroke(0.4.dp, colorResource(id = R.color.stroke_color))
        ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(Padding8),
            verticalAlignment = Alignment.Top,
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(30.dp)
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
                    text = "DynamicWebPaige",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(Padding4))
                Text(
                    text = "This is a random bio, which will be replace with actual content",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.W500
                )
                Spacer(modifier = Modifier.height(Padding4))
                Row {
                    Text(
                        text = "Lagos, Nigeria",
                        fontSize = 10.sp,
                        color = colorResource(id = R.color.grey),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.width(Padding6))
                    Text(
                        text = "momoko@gmail.com",
                        fontSize = 10.sp,
                        color = colorResource(id = R.color.grey),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun UserTilePreview() {
    UserTile(
        modifier = Modifier.fillMaxWidth(),
        user = User(
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
        ), onClick = {}
    )
}