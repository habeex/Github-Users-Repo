package com.example.githubrepo.presentation.users.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.githubrepo.R
import com.example.githubrepo.presentation.Dimens.Padding4
import com.example.githubrepo.presentation.Dimens.Padding6
import com.example.githubrepo.presentation.Dimens.Padding8

fun Modifier.shimmerEffect(cornerRadius: CornerRadius = CornerRadius(x = 12f, y = 12f)) = composed {
    val transition = rememberInfiniteTransition(label = "shimmer effect")
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "transparency of the background color"
    ).value
    val color = colorResource(id = R.color.shimmer).copy(alpha = alpha)
    drawBehind {
        drawRoundRect(
            color = color,
            cornerRadius = cornerRadius
        )
    }
}

@Composable
fun UserCardShimmerEffect(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.0.dp,
            draggedElevation = 0.0.dp,
            focusedElevation = 0.0.dp,
            disabledElevation = 0.0.dp,
        ),
        border = BorderStroke(0.4.dp, colorResource(id = R.color.stroke_color))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(Padding8),
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
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
                Spacer(modifier = Modifier.height(Padding4))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(Padding4))
                Row {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .height(10.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.width(Padding6))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .height(10.dp)
                            .shimmerEffect()
                    )
                }
            }
        }
    }
}
