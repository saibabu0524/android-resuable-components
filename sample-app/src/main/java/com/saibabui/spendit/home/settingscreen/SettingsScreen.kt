package com.saibabui.spendit.home.settingscreen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.collection.BuildConfig.APPLICATION_ID
import com.google.firebase.storage.storage
import com.saibabui.spendit.common.FirebaseUtils
import com.saibabui.spendit.home.homescreen.presentation.CommonHeader
import com.saibabui.spendit.R
import com.saibabui.spendit.common.Graph
import com.saibabui.spendit.ui.theme.SpendItTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects.requireNonNull

@Composable
fun SettingsScreen(
    navController: NavController,
    fetchProfileImage: () -> Uri = { com.saibabui.spendit.home.settingscreen.fetchProfileImage() }
) {

    var showAlertDialog by remember {
        mutableStateOf(true)
    }
    var userProfileImage by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.IO){
            val uri = fetchProfileImage()
            if (uri.path?.isNotEmpty() == true) {
                userProfileImage = uri
                Log.d("Fetched profile image", uri.path.toString())
            }
        }
    }

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        requireNonNull(context), "${APPLICATION_ID}.provider", file
    )
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { result ->
            if (result) {
                userProfileImage = uri
                uploadImageToFireBase(uri)
                showAlertDialog = false
            }
        }
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                Toast.makeText(context, "Camera permissions granted", Toast.LENGTH_SHORT).show()
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Camera permissions denied", Toast.LENGTH_SHORT).show()
            }
        }

    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold { paddingValues1 ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    CommonHeader(
                        "Settings",
                        null,
                        modifier = Modifier.background(Color.Transparent),
                        leadingIcon = painterResource(id = R.drawable.back_icon)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                            )
                            .padding(24.dp),
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.onSurface,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .width(40.dp)
                                    .height(4.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (userProfileImage.path?.isEmpty() == true) {
                                        Image(painter = painterResource(id = R.drawable.sample_profile_image),
                                            contentDescription = "Profile Image",
                                            modifier = Modifier
                                                .width(60.dp)
                                                .height(60.dp)
                                                .clickable {
                                                    showAlertDialog = true
                                                })
                                    } else {
                                        AsyncImage(
                                            model = userProfileImage,
                                            contentDescription = "profile image",
                                            modifier = Modifier
                                                .width(60.dp)
                                                .height(60.dp)
                                                .clip(CircleShape)
                                                .clickable {
                                                    showAlertDialog = true
                                                },
                                            contentScale = ContentScale.Crop
                                        )
                                    }

                                    Column(modifier = Modifier.padding(start = 12.dp)) {
                                        Text(
                                            text = "Nazrul Islam",
                                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = "Never give up \uD83D\uDCAA")
                                    }
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.qr_code_image),
                                        contentDescription = "Qr Code Icon",
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp),
                                        tint = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                            Divider(
                                Modifier
                                    .padding(top = 16.dp)
                                    .height(2.dp)
                                    .background(MaterialTheme.colorScheme.onSurfaceVariant)


                            )

                            Button(
                                onClick = {
                                    Firebase.auth.signOut()
                                    navController.navigate(Graph.AUTHENTICATION)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = "Log out",
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }


                }
            }
        }
        if (showAlertDialog) {
            AddProfileImage({
                if (ContextCompat.checkSelfPermission(
                        context, Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(context, "Camera permissions granted", Toast.LENGTH_SHORT).show()
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }) {
                showAlertDialog = false
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): File {

    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName, ".jpg", externalCacheDir
    )
}

@Composable
fun AddProfileImage(
    takePictureFromCamera: () -> Unit, changeAlertDialogVisibility: () -> Unit
) {
    androidx.compose.material.AlertDialog(onDismissRequest = {
        changeAlertDialogVisibility()
    }, buttons = {
        Row {
            Button(onClick = {
                takePictureFromCamera()
            }) {
                Text(
                    text = "Camera"
                )
            }
            Button(onClick = { /*TODO*/ }) {
                Text(
                    text = "Gallery"
                )
            }
        }
    }, title = {
        Text(text = "Alert Dialog")
    }, text = { Text(text = "Please select Camera or Gallery to update profile picture") })
}


fun uploadImageToFireBase(uri: Uri) {
    val storage = Firebase.storage
    val storageRef = storage.reference
    val imageRef = storageRef.child("images").child("userProfiles")
    val fileName = "${FirebaseUtils.getCurrentUserId()}.jpg"
    val profileImageRef = imageRef.child(fileName)
    val uploadTask = profileImageRef.putFile(uri)
    uploadTask.addOnFailureListener {
        Log.d("Image Upload", it.message ?: "Failed to upload image")
    }.addOnSuccessListener {
        Log.d("Image Upload", "Image upload success")
    }
}

fun fetchProfileImage(): Uri {
    val storage = Firebase.storage
    val storageRef = storage.reference
    val imageRef = storageRef.child("images").child("userProfiles")
    val fileName = "${FirebaseUtils.getCurrentUserId()}.jpg"
    var uri = Uri.parse("")
    val profileImageRef = imageRef.child(fileName)
    profileImageRef.downloadUrl.addOnCompleteListener {
        if (it.isSuccessful) {
            uri = it.result
            Log.d("Fetched profile image", it.result.path.toString())
        }
    }
    return uri
}

@Preview(showSystemUi = true)
@Composable
private fun SettingsScreenPrev() {
    SpendItTheme(darkTheme = false) {
        SettingsScreen(NavController(LocalContext.current))
    }
}