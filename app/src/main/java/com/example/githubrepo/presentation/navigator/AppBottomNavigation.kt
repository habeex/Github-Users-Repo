package com.example.githubrepo.presentation.navigator

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubrepo.R
import com.example.githubrepo.presentation.Dimens.Padding6
import com.example.githubrepo.presentation.Dimens.IconSize
import com.example.githubrepo.ui.theme.GithubRepoTheme

@Composable
fun AppBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary,
        ),
        containerColor = MaterialTheme.colorScheme.secondary,
        tonalElevation = 0.dp,
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selectedItem
            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = if(isSelected) item.selectedIcon else item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize),
                        )
                        Spacer(modifier = Modifier.height(Padding6))
                        Text(text = item.text, style = if (isSelected) MaterialTheme.typography.labelLarge else MaterialTheme.typography.bodySmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.secondary
                ),
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
    val text: String
)

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationPreview() {
    GithubRepoTheme(dynamicColor = false) {
        AppBottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, selectedIcon = R.drawable.ic_selcted_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, selectedIcon = R.drawable.ic_selected_search, text = "Repositories"),
            BottomNavigationItem(icon = R.drawable.ic_user, selectedIcon = R.drawable.ic_selected_user, text = "Users"),
        ), selectedItem = 0, onItemClick = {})
    }
}