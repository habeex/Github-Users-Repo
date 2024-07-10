package com.example.githubrepo.presentation.userdetails.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubrepo.R
import com.example.githubrepo.presentation.Dimens.Padding12
import com.example.githubrepo.presentation.Dimens.Padding16
import com.example.githubrepo.presentation.Dimens.Padding2
import com.example.githubrepo.presentation.Dimens.Padding20
import com.example.githubrepo.presentation.Dimens.Padding4
import com.example.githubrepo.presentation.Dimens.Padding8
import com.example.githubrepo.presentation.common.shimmerEffect

@Composable
fun UserRepoShimmerEffect(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = Padding20)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(Padding8))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(10.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(Padding4))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(10.dp)
                        .shimmerEffect()
                )
            }
        }
        Spacer(modifier = Modifier.height(Padding16))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(Padding16))
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(Padding4))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .height(10.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(Padding8))
            Icon(
                painter = painterResource(id = R.drawable.ic_link),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(Padding4))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .height(10.dp)
                    .shimmerEffect()
            )

        }
        Spacer(modifier = Modifier.height(Padding12))
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_people),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(Padding4))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(10.dp)
                    .shimmerEffect()
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
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.lighter_grey))
            )
        }
        Spacer(modifier = Modifier.height(Padding8))
        Row {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp
            )
            HorizontalDivider(
                modifier = Modifier
                    .weight(1.85f),
                color = colorResource(id = R.color.lighter_grey),
                thickness = 2.dp
            )
        }
        UserRepoListShimmerEffect()
    }
}

@Composable
fun UserRepoListShimmerEffect(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = Padding16),
        verticalArrangement = Arrangement.spacedBy(Padding8),
        contentPadding = PaddingValues(vertical = Padding8)
    ) {
        items(10) {
            UserRepoTileShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun UserRepoTileShimmerEffect(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
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
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .height(10.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.width(Padding8))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .height(12.dp)
                            .shimmerEffect()
                    )
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.07f)
                                .height(10.dp)
                                .shimmerEffect()
                        )
                    }
                    Spacer(modifier = Modifier.width(Padding4))
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.07f)
                            .height(10.dp)
                            .shimmerEffect()
                    )
                }
            }
            Spacer(modifier = Modifier.height(Padding4))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(Padding4))
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.1f)
                        .height(10.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(Padding8))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.1f)
                        .height(10.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}