package com.example.praktam2_2417051026

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praktam2_2417051026.Model.Tracker

@Composable
fun MainPage() {

    var selectedMenu by remember { mutableStateOf("home") }
    val snackbarHostState = remember { SnackbarHostState() }

    var trackers by remember { mutableStateOf<List<Tracker>>(emptyList()) }
    var recordedDaysMap by remember { mutableStateOf(mapOf<Int, Tracker>()) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(30.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 10.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = { selectedMenu = "home" },
                            modifier = Modifier
                                .height(40.dp)
                                .defaultMinSize(minHeight = 1.dp),
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("Home")
                        }

                        Button(
                            onClick = { selectedMenu = "calendar" },
                            modifier = Modifier
                                .height(40.dp)
                                .defaultMinSize(minHeight = 1.dp),
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("Calendar")
                        }

                        Button(
                            onClick = { selectedMenu = "selfcare" },
                            modifier = Modifier
                                .height(40.dp)
                                .defaultMinSize(minHeight = 1.dp),
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("self care")
                        }

                        Button(
                            onClick = { selectedMenu = "profile" },
                            modifier = Modifier
                                .height(40.dp)
                                .defaultMinSize(minHeight = 1.dp),
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("Profile")
                        }
                    }
                }
            }
        }
    ) { padding ->

        Box(modifier = Modifier.padding(padding)) {

            when (selectedMenu) {

                "home" -> TrackerScreen(
                    snackbarHostState = snackbarHostState,
                    trackers = trackers,
                    onTrackersLoaded = { fetchedTrackers -> trackers = fetchedTrackers },
                    recordedDaysMap = recordedDaysMap,
                    onSaveRecordedDay = { day, tracker ->
                        recordedDaysMap = recordedDaysMap + (day to tracker)
                    }
                )
                "calendar" -> CalendarOnlyScreen(recordedDaysMap = recordedDaysMap)

                "selfcare" -> SelfCareScreen()

                "profile" -> ProfileScreen(snackbarHostState = snackbarHostState)
            }
        }
    }
}
