package com.saibabui.spendit.auth.singup.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.saibabui.base.TopAppBarComposable
import com.saibabui.spendit.auth.CustomButton
import com.saibabui.spendit.auth.EmailTextFieldWithLabel
import com.saibabui.spendit.auth.LoginDescription
import com.saibabui.spendit.auth.LoginHeadingText
import com.saibabui.spendit.auth.TopBar
import com.saibabui.spendit.auth.model.SocialMediaIcon
import com.saibabui.spendit.auth.singup.presentation.model.SignUpFormEvents
import com.saibabui.spendit.auth.singup.presentation.viewmodel.SignUpViewmodel
import com.saibabui.spendit.R

@Composable
fun SignUpScreen(navController: NavController) {

    val viewmodel: SignUpViewmodel = hiltViewModel()
    val db = Firebase.firestore

    Scaffold(topBar = {
        TopAppBarComposable(arrowBackIcon = R.drawable.back_icon) {
            navController.popBackStack()
        }
    }) { paddingValues ->
        SignUpBody(paddingValues, viewmodel, navController = navController)
        SignIn(
            navController = navController,
            signUpSucceed = { addUserToFireStore(viewmodel, db) }) {
            Log.d("Firebase", "Failed")
        }
    }
}

val addUserToFireStore: (viewModel: SignUpViewmodel, db: FirebaseFirestore) -> Unit =
    { viewModel, db ->
        val TAG = "FIREBASE FIRESTORE"
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: run {
            Log.w(TAG, "No user is currently logged in.")
            return@run
        }

        val userData = hashMapOf(
            "name" to viewModel.nameState.value.value,
            "email" to viewModel.emailState.value.value,
            "userId" to userId
        )

        Log.d(TAG, "Adding to Firestore")

        db.collection("users")
            .document(userId.toString())
            .set(userData)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully written with ID: $userId")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(NavController(LocalContext.current))
}


@Composable
fun SignUpBody(
    paddingValues: PaddingValues,
    viewmodel: SignUpViewmodel,
    navController: NavController
) {
    val socialMediaIcons = listOf(
        SocialMediaIcon(
            painter = painterResource(
                id = R.drawable.facebook,
            ), stringResource(R.string.facebook_icon)
        ), SocialMediaIcon(
            painter = painterResource(
                id = R.drawable.google,
            ), stringResource(R.string.google_icon)
        ), SocialMediaIcon(
            painter = painterResource(
                id = R.drawable.apple,
            ), stringResource(R.string.apple_icon)
        )
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .verticalScroll(rememberScrollState())
            .imePadding()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            LoginHeadingText(stringResource(R.string.sign_up_with_email))
            LoginDescription(stringResource(R.string.get_order_meat_and_seafood_from_local_farmers_today_by_signing_up_for_our_app))
            Spacer(modifier = Modifier.height(16.dp))
            EmailTextFieldWithLabel(
                supportingText = stringResource(R.string.name), onValueChange = {
                    viewmodel.validate(
                        SignUpFormEvents.NameChangedEvent(it)
                    )
                }, internalState = viewmodel.nameState
            )
            EmailTextFieldWithLabel(
                supportingText = stringResource(R.string.your_email), onValueChange = {
                    viewmodel.validate(
                        SignUpFormEvents.EmailChangedEvent(it)
                    )
                }, internalState = viewmodel.emailState
            )
            EmailTextFieldWithLabel(
                stringResource(R.string.password),
                internalState = viewmodel.passwordState
            ) {
                viewmodel.validate(
                    SignUpFormEvents.PasswordChangedEvent(it)
                )

            }
            EmailTextFieldWithLabel(
                stringResource(R.string.confirm_password),
                internalState = viewmodel.repeatedPasswordState
            ) {
                viewmodel.validate(
                    SignUpFormEvents.RepeatedPasswordChangedEvent(it)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 20.dp)
        ) {
            CustomButton(
                stringResource(R.string.create_an_account),
                signUp = { viewmodel.validate(SignUpFormEvents.SignUpButtonEvent) }
            )
        }
    }
}
