package com.saibabui.spendit.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.saibabui.spendit.auth.singup.presentation.model.CustomTextFieldState
import com.saibabui.spendit.R
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun LoginHeadingText(headingText: String) {
    Text(
        text = headingText,
        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
}


@Composable
fun LoginDescription(description: String) {
    Text(
        text = description,
        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center
    )
}


private val CustomLabelForTextField: @Composable (labelText: String, textColor: Color) -> Unit =
    { text, textColor ->
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }

val CustomButtonText: @Composable (String) -> Unit = {
    Text(
        text = it,
        fontSize = 16.sp,
        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun EmailTextFieldWithLabel(
    supportingText: String,
    internalState: MutableStateFlow<CustomTextFieldState>,
    onValueChange: (String) -> Unit = {}
) {
    val state = internalState.collectAsState().value
    Column {
        CustomLabelForTextField(
            supportingText,
            if (state.error.isEmpty()) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
        )
        TextField(
            value = state.value,
            onValueChange = {
                onValueChange(it)
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                errorIndicatorColor = MaterialTheme.colorScheme.error,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
            ),
            modifier = Modifier.fillMaxWidth(),
            isError = state.error.isNotEmpty(),
            supportingText = {
                if (state.error.isNotEmpty()) {
                    Text(
                        text = state.error,
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            maxLines = 1
        )
    }
}

@Composable
fun PasswordTextFieldWithLabel(
    supportingText: String,
    internalState: MutableStateFlow<CustomTextFieldState>,
    onValueChange: (String) -> Unit = {}
) {
    val state = internalState.collectAsState().value
    Column {
        CustomLabelForTextField(
            supportingText,
            if (state.error.isEmpty()) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
        )
        TextField(
            value = state.value,
            onValueChange = {
                onValueChange(it)
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                errorIndicatorColor = MaterialTheme.colorScheme.error,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
            ),
            modifier = Modifier.fillMaxWidth(),
            isError = state.error.isNotEmpty(),
            supportingText = {
                if (state.error.isNotEmpty()) {
                    Text(
                        text = state.error,
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            maxLines = 1
        )
    }
}

@Composable
fun CustomButton(buttonText: String, modifier: Modifier = Modifier, signUp: () -> Unit) {
    Button(
        onClick = {
            signUp()
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp))
            .then(modifier)
    ) {
        CustomButtonText(buttonText)
    }
}


@Composable
fun NavigationText() {
    Text(text = stringResource(R.string.forgot_password), fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
}


@Composable
fun TopBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back_icon),
            contentDescription = stringResource(R.string.back_navigation_icon),
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
                .clickable {
                    navController.popBackStack()
                }

        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
}