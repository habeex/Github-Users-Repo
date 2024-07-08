package com.example.githubrepo.presentation.common

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.githubrepo.util.UIComponent

object ErrorComponent {
    @Composable
    fun Show(uiComponent: UIComponent, onClick: () -> Unit){
        val context = LocalContext.current
        when (uiComponent){
            is UIComponent.Dialog -> {
                AlertDialog(
                    onDismissRequest = {},
                    title = { Text(text = uiComponent.title) },
                    text = { Text(text = uiComponent.message) },
                    confirmButton = {
                        CustomButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Search",
                            onClick = onClick,
                        )
                    }
                )
            }
            is UIComponent.None -> TODO()
            is UIComponent.Toast -> {
                Toast.makeText(context, uiComponent.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}