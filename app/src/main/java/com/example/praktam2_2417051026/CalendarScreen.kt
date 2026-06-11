package com.example.praktam2_2417051026

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.praktam2_2417051026.Model.Tracker
import com.example.praktam2_2417051026.ui.theme.PinkPrimary
import com.example.praktam2_2417051026.ui.theme.PinkSecondary

@Composable
fun CalendarScreen(
    recordedDaysMap: Map<Int, Tracker>,
    onRecordDay: (Int) -> Unit
) {
    var selectedDay by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Juni 2026",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = PinkPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val daysOfWeek = listOf("Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab")
            daysOfWeek.forEach { dayOfWeek ->
                Text(
                    text = dayOfWeek,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (dayOfWeek == "Min") PinkPrimary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // calendar
        val daysInMonth = 30
        val emptySlotsAtStart = 1
        val cells = List(emptySlotsAtStart) { null } + (1..daysInMonth).toList()
        val rows = cells.chunked(7)

        rows.forEach { rowCells ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowCells.forEach { day ->
                    if (day == null) {
                        Spacer(modifier = Modifier.weight(1f))
                    } else {
                        val tracker = recordedDaysMap[day]
                        val isRecorded = tracker != null

                        val backgroundColor = when {
                            isRecorded -> {
                                when (tracker?.skala) {
                                    "1" -> Color(0xFFFFECEF)
                                    "2" -> Color(0xFFFFCCD3)
                                    "3" -> Color(0xFFFF99A8)
                                    "4" -> Color(0xFFFF667D)
                                    "5" -> PinkPrimary
                                    else -> PinkSecondary
                                }
                            }
                            else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        }

                        val textColor = when {
                            isRecorded && tracker?.skala == "5" -> Color.White
                            else -> MaterialTheme.colorScheme.onSurface
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(2.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(backgroundColor)
                                .clickable {
                                    if (isRecorded) {
                                        selectedDay = day
                                    } else {
                                        onRecordDay(day)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = day.toString(),
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = if (isRecorded) FontWeight.Bold else FontWeight.Normal,
                                    color = textColor
                                )
                                if (isRecorded) {
                                    Text(
                                        text = "S${tracker?.skala}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = if (tracker?.skala == "5") Color.White else PinkPrimary,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
                if (rowCells.size < 7) {
                    Spacer(modifier = Modifier.weight((7 - rowCells.size).toFloat()))
                }
            }
        }

        selectedDay?.let { day ->
            val tracker = recordedDaysMap[day]
            if (tracker != null) {
                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val context = LocalContext.current
                            val imageResId = remember(tracker.imageName) {
                                val resId = context.resources.getIdentifier(tracker.imageName, "drawable", context.packageName)
                                if (resId != 0) resId else R.drawable.emoji2
                            }
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = "Skala ${tracker.skala}",
                                modifier = Modifier.size(60.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Catatan Tanggal $day Juni 2026",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Skala Nyeri: ${tracker.skala}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = PinkPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Deskripsi:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        Text(
                            text = tracker.deskripsi,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarOnlyScreen(recordedDaysMap: Map<Int, Tracker>) {
    CalendarScreen(
        recordedDaysMap = recordedDaysMap,
        onRecordDay = {}
    )
}
