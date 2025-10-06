package com.org.filmsapplication.core.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.org.filmsapplication.core.ui.theme.SnackBarBg
import com.org.filmsapplication.core.ui.theme.Yellow
import com.org.filmsapplication.core.ui.theme.robotoFamily

@Composable
fun ErrorSnackBar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
) {
    val actionLabel = snackbarData.visuals.actionLabel
    val actionComposable: (@Composable () -> Unit)? =
        if (actionLabel != null) {
            @Composable {
                TextButton(
                    colors = ButtonDefaults.textButtonColors(contentColor = Yellow),
                    onClick = { snackbarData.performAction() },
                    content = {
                        Text(
                            actionLabel,
                            fontFamily = robotoFamily
                        )
                    }
                )
            }
        } else {
            null
        }

    Snackbar(
        modifier = modifier.padding(8.dp),
        action = actionComposable,
        actionOnNewLine = false,
        shape = SnackbarDefaults.shape,
        containerColor = SnackBarBg,
        content = {
            Text(
                snackbarData.visuals.message,
                fontFamily = robotoFamily
            )
        }
    )
}

