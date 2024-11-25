package com.saibabui.spendit.home.contactscreen

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saibabui.spendit.auth.navigation.Home
import com.saibabui.spendit.home.homescreen.presentation.CommonHeader
import com.saibabui.spendit.home.profile.shimmerEffect
import com.saibabui.spendit.R


@Composable
fun ContactScreen(
    contactViewModel: ContactViewModel = hiltViewModel(),
    navController: NavController,
) {
    val contactDetailsState = contactViewModel.contacts.collectAsState()
    Scaffold { paddingValues1 ->
        Column(
            modifier = Modifier
                .fillMaxSize().background(MaterialTheme.colorScheme.surface)
        ) {
            CommonHeader(
                screenName = "Contact",
                icon = painterResource(id = R.drawable.user_add_header_icon),
                modifier = Modifier
                    .width(41.dp)
                    .height(41.dp)
                    .background(Color(0x33FFFFFF), shape = CircleShape)
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            if (contactViewModel.loading.collectAsState().value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                        )
                        .padding(24.dp),
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .background( MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(10.dp))
                                .width(40.dp)
                                .height(4.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "My Contact", fontFamily = MaterialTheme.typography.bodyMedium.fontFamily, fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color(0xff000E08),
                            lineHeight = 16.sp
                        )
                        LazyColumn {
                            items(10) { contact ->
                                ContactShimmerBody()
                            }
                        }
                    }
                }
            } else {
                CategorizedLazyColumn(contactDetailsState.value, navController)
            }
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorizedLazyColumn(
    contactDetails: List<ContactDetails>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val groupedContacts = groupByFirstLetter(contactDetails)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .padding(24.dp),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(Color(0xFFE6E6E6), shape = RoundedCornerShape(10.dp))
                    .width(40.dp)
                    .height(4.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "My Contact", fontFamily = MaterialTheme.typography.bodyMedium.fontFamily, fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 16.sp
            )
            LazyColumn(modifier = modifier) {
                groupedContacts.forEach { (letter, contacts) ->
                    stickyHeader {
                        Text(
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            text = letter.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                        )
                    }
                    items(contacts) { contact ->
                        ContactCard(contactDetails = contact, navController)
                    }
                }
            }
        }
    }
}

private fun groupByFirstLetter(contactDetails: List<ContactDetails>): Map<Char, List<ContactDetails>> {
    return contactDetails.groupBy { it.name.first().uppercaseChar() }
}


@Composable
fun ContactCard(
    contactDetails: ContactDetails,
    navController: NavController
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .clickable {
                navController.navigate(
                    Home.ProfileScreen.route + "/${contactDetails.name}/${contactDetails.email}/${contactDetails.userId}"
                )
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.sample_profile_image),
            contentDescription = stringResource(R.string.profile_photo),
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp)) // Add some space between image and text
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = contactDetails.name.replaceFirstChar {
                    it.uppercaseChar()
                },
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
            )
            Text(
                text = contactDetails.email,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = Color(0x797C7B80)
            )
        }
    }
}


@Composable
fun ContactShimmerBody() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .width((50..200).random().dp)
                    .height(20.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width((150..300).random().dp)
                    .height(10.dp)
                    .shimmerEffect()
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun ContactCardPrv() {

}