package com.saibabui.base

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComposable(
    title: String? = null,
    arrowBackIcon: Int? = null,
    onBackArrowClicked: () -> Unit
) {
    TopAppBar(
        title = {
            if (title != null) {
                Text(text = title)
            }
        },
        navigationIcon = {
            if (arrowBackIcon != null) {
                BackArrowIcon(
                    arrowBackIcon = arrowBackIcon,
                    contentDescription = null,
                    onBackArrowClicked = onBackArrowClicked
                )
            }
        },
    )
}

@Composable
fun BackArrowIcon(arrowBackIcon: Int, contentDescription: String?, onBackArrowClicked: () -> Unit) {
    IconButton(onClick = onBackArrowClicked) {
        Icon(
            painter = painterResource(id = arrowBackIcon),
            contentDescription = contentDescription
        )
    }

}


@Preview(showBackground = true)
@Composable
fun TopAppBarComposablePreview() {
    TopAppBarComposable(title = "Top App Bar", arrowBackIcon = null, onBackArrowClicked = {})
}

