package com.example.praktam2_2417051026

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktam2_2417051026.ui.theme.PinkPrimary
import com.example.praktam2_2417051026.ui.theme.PinkSecondary
import com.example.praktam2_2417051026.ui.theme.PinkBackground
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(snackbarHostState: SnackbarHostState) {
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf("Kamila Putri Hasan") }
    var dob by remember { mutableStateOf("08 Juni 2006") }
    var email by remember { mutableStateOf("kamilaputrihasan@gmail.com") }
    var cycleLength by remember { mutableStateOf("28 Hari") }
    var periodLength by remember { mutableStateOf("7 Hari") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(PinkPrimary, PinkSecondary)
                    )
                )
        ) {
            Text(
                text = "Profil Saya",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 40.dp)
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.petrikk),
                        contentDescription = "Foto Profil",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = (-20).dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Informasi Pribadi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PinkPrimary
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nama Lengkap") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = PinkPrimary) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        unfocusedBorderColor = PinkSecondary,
                        focusedLabelColor = PinkPrimary,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = PinkPrimary
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = dob,
                    onValueChange = { dob = it },
                    label = { Text("Tanggal Lahir") },
                    leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null, tint = PinkPrimary) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        unfocusedBorderColor = PinkSecondary,
                        focusedLabelColor = PinkPrimary,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = PinkPrimary
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = PinkPrimary) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        unfocusedBorderColor = PinkSecondary,
                        focusedLabelColor = PinkPrimary,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = PinkPrimary
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = cycleLength,
                    onValueChange = { cycleLength = it },
                    label = { Text("Rata-rata Siklus") },
                    leadingIcon = { Icon(Icons.Default.Refresh, contentDescription = null, tint = PinkPrimary) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        unfocusedBorderColor = PinkSecondary,
                        focusedLabelColor = PinkPrimary,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = PinkPrimary
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = periodLength,
                    onValueChange = { periodLength = it },
                    label = { Text("Durasi Menstruasi") },
                    leadingIcon = { Icon(Icons.Default.Info, contentDescription = null, tint = PinkPrimary) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        unfocusedBorderColor = PinkSecondary,
                        focusedLabelColor = PinkPrimary,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = PinkPrimary
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Profil berhasil disimpan!")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Simpan Perubahan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}