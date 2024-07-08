package com.example.githubrepo.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubrepo.R
import com.example.githubrepo.presentation.Dimens.Padding16
import com.example.githubrepo.ui.theme.GithubRepoTheme

@Composable
fun HomeCardItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    backgroundColor: Color,
    text: String,
    onClick: () -> Unit
){
    Card(
        modifier = modifier
            .height(118.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.0.dp
        ),
        border = BorderStroke(0.4.dp, colorResource(id = R.color.stroke_color))
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Padding16),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeCardItemPreview() {
    GithubRepoTheme(dynamicColor = false) {
        HomeCardItem(
            icon = R.drawable.ic_wallet_rounded,
            backgroundColor = colorResource(id = R.color.light_purple),
            text = "Users"
        ) {

        }
    }
}