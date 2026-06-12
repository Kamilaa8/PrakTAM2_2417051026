package com.example.praktam2_2417051026

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.praktam2_2417051026.data.api.RetrofitClient
import com.example.praktam2_2417051026.data.model.Tracker
import com.example.praktam2_2417051026.data.repository.TrackerRepository
import com.example.praktam2_2417051026.ui.theme.PinkPrimary
import com.example.praktam2_2417051026.ui.theme.PinkSecondary
import com.example.praktam2_2417051026.ui.theme.PinkBackground

@Composable
fun TrackerScreen(
    snackbarHostState: SnackbarHostState,
    trackers: List<Tracker>,
    onTrackersLoaded: (List<Tracker>) -> Unit,
    recordedDaysMap: Map<Int, Tracker>,
    onSaveRecordedDay: (Int, Tracker) -> Unit
) {
    var showStart by remember { mutableStateOf(true) }
    var showCalendar by remember { mutableStateOf(false) }
    var showTracker by remember { mutableStateOf(false) }
    var selectedTracker by remember { mutableStateOf<Tracker?>(null) }
    var activeDay by remember { mutableStateOf<Int?>(null) }

    var isLoadingApi by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    val trackerRepository = remember { TrackerRepository(RetrofitClient.instance) }

    LaunchedEffect(Unit) {
        try {
            Log.d("TrackerScreen", "Fetching trackers via Repository...")
            val response = trackerRepository.getTrackers()
            Log.d("TrackerScreen", "Successfully fetched ${response.size} trackers")
            onTrackersLoaded(response)
            isLoadingApi = false
        } catch (e: Exception) {
            Log.e("TrackerScreen", "Error fetching trackers", e)
            errorMessage = e.localizedMessage ?: "Unknown error"
            isLoadingApi = false
            isError = true
        }
    }

    if (showStart) {
        StartScreen {
            showStart = false
            showCalendar = true
        }
    } else if (showCalendar) {
        CalendarScreen(
            recordedDaysMap = recordedDaysMap,
            onRecordDay = { day: Int ->
                activeDay = day
                showCalendar = false
                showTracker = true
            }
        )
    } else if (showTracker && selectedTracker == null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(PinkBackground)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Skala Kram",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = PinkPrimary
                    )
                    TextButton(onClick = {
                        showTracker = false
                        showCalendar = true
                    }) {
                        Text("Batal", color = PinkPrimary, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Pilih skala yang paling menggambarkan rasa nyeri kamu saat ini.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            if (isLoadingApi) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PinkPrimary)
                    }
                }
            } else if (isError) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Gagal memuat data: $errorMessage", color = PinkPrimary)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = {
                                    isLoadingApi = true
                                    isError = false
                                    isLoadingApi = true
                                    try {
                                    } catch (e: Exception) {}
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary)
                            ) {
                                  Text("Coba Lagi")
                            }
                        }
                    }
                }
            } else {
                items(trackers) { tracker ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            val imageResId = remember(tracker.imageName) {
                                val resId = context.resources.getIdentifier(tracker.imageName, "drawable", context.packageName)
                                if (resId != 0) resId else R.drawable.emoji2
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(PinkBackground),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        model = imageResId,
                                        contentDescription = "Skala ${tracker.skala}",
                                        placeholder = painterResource(id = R.drawable.menstrual),
                                        error = painterResource(id = R.drawable.emoji2),
                                        modifier = Modifier.fillMaxSize().padding(4.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text(
                                        text = "Skala Nyeri ${tracker.skala}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = PinkPrimary
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = tracker.deskripsi,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = { selectedTracker = tracker },
                                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Lihat detail", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    } else if (selectedTracker != null) {
        DetailScreen(
            tracker = selectedTracker!!,
            onBack = { selectedTracker = null },
            onSave = {
                activeDay?.let { day ->
                    onSaveRecordedDay(day, selectedTracker!!)
                }
                selectedTracker = null
                showTracker = false
                showCalendar = true
            },
            snackbarHostState = snackbarHostState
        )
    }
}
