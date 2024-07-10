package com.example.githubrepo.presentation.repositories.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.githubrepo.R
import com.example.githubrepo.presentation.Dimens.Padding16
import com.example.githubrepo.presentation.Dimens.Padding2
import com.example.githubrepo.presentation.Dimens.Padding4
import com.example.githubrepo.presentation.Dimens.Padding8
import com.example.githubrepo.presentation.common.shimmerEffect

@Composable
fun RepositoryTileShimmerEffect(modifier: Modifier = Modifier) {
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
                        .fillMaxWidth(0.2f)
                        .height(12.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(Padding8))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .height(12.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(Padding8))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .height(12.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}