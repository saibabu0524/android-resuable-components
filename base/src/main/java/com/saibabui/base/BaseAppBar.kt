package com.saibabui.base

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * A reusable top app bar composable with optional back navigation.
 *
 * @param title The title to display in the app bar. Null if no title is needed.
 * @param arrowBackIcon Resource ID for the back arrow icon. Null if no back button is needed.
 * @param onBackArrowClicked Callback invoked when the back arrow is clicked.
 *
 * @sample TopAppBarComposablePreview
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComposable(
    title: String? = null,
    arrowBackIcon: Int? = null,
    onBackArrowClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            title?.let {
                Text(text = it)
            }
        },
        navigationIcon = {
            arrowBackIcon?.let {
                BackArrowIcon(
                    arrowBackIcon = it,
                    contentDescription = "Navigate back",
                    onBackArrowClicked = onBackArrowClicked
                )
            }
        }
    )
}

/**
 * A back arrow icon button.
 *
 * @param arrowBackIcon Resource ID for the back arrow icon.
 * @param contentDescription Content description for accessibility.
 * @param onBackArrowClicked Callback invoked when the icon is clicked.
 */
@Composable
fun BackArrowIcon(
    arrowBackIcon: Int,
    contentDescription: String?,
    onBackArrowClicked: () -> Unit
) {
    IconButton(onClick = onBackArrowClicked) {
        Icon(
            painter = painterResource(id = arrowBackIcon),
            contentDescription = contentDescription
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarComposablePreview() {
    TopAppBarComposable(
        title = "Top App Bar",
        arrowBackIcon = null,
        onBackArrowClicked = {}
    )
}
