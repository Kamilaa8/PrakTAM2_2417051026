package com.example.praktam2_2417051026

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.praktam2_2417051026.ui.theme.PinkPrimary
import com.example.praktam2_2417051026.ui.theme.PinkSecondary
import com.example.praktam2_2417051026.ui.theme.PinkBackground
import kotlinx.coroutines.delay

data class ReliefItem(
    val title: String,
    val emoji: String,
    val description: String,
    val durationSeconds: Int
)

data class WhiteNoiseItem(
    val name: String,
    val emoji: String
)

@Composable
fun SelfCareScreen() {
    var activeActivity by remember { mutableStateOf<ReliefItem?>(null) }
    var activeNoise by remember { mutableStateOf<WhiteNoiseItem?>(null) }

    val noises = listOf(
        WhiteNoiseItem("Windy", "🍃"),
        WhiteNoiseItem("Rain", "🌧️"),
        WhiteNoiseItem("Night", "🌙"),
        WhiteNoiseItem("Ocean", "🌊")
    )

    val reliefs = listOf(
        ReliefItem(
            "Period Pain Relief",
            "🧘‍♀️",
            "Latihan pernapasan dalam dan relaksasi untuk meredakan kram perut.",
            30
        ),
        ReliefItem(
            "Foot Massage",
            "🦶",
            "Pijat refleksi ringan pada area kaki untuk merangsang sirkulasi darah.",
            45
        ),
        ReliefItem(
            "Light Stretching",
            "🤸‍♀️",
            "Peregangan ringan untuk melenturkan otot pinggang dan pinggul.",
            60
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Self Care",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = PinkPrimary
        )

        Text(
            text = "Tenangkan tubuh dan pikiranmu selama siklus haid.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "White Noise Suara Santai",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = PinkPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(noises) { item ->
                val isSelected = activeNoise == item
                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            activeNoise = if (isSelected) null else item
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) PinkSecondary else Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.emoji,
                            fontSize = 32.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = item.name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        if (activeNoise != null) {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = PinkPrimary.copy(alpha = 0.15f))
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🔊 Memutar suara ${activeNoise!!.name} ${activeNoise!!.emoji} untuk relaksasi...",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = PinkPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Menstrual Relief & Terapi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = PinkPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            reliefs.forEach { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(PinkBackground),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item.emoji,
                                fontSize = 30.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = item.title,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.description,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = { activeActivity = item },
                                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
                            ) {
                                Text("Mulai (${item.durationSeconds} Detik)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
    }

    activeActivity?.let { activity ->
        TimerDialog(
            activity = activity,
            onDismiss = { activeActivity = null }
        )
    }
}

@Composable
fun TimerDialog(
    activity: ReliefItem,
    onDismiss: () -> Unit
) {
    var timeLeft by remember { mutableStateOf(activity.durationSeconds) }
    var isFinished by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
        isFinished = true
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = activity.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PinkPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .border(4.dp, PinkSecondary, CircleShape)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = if (isFinished) "🎉" else activity.emoji,
                            fontSize = 40.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isFinished) "Selesai!" else "$timeLeft",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = PinkPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = if (isFinished)
                        "Hebat! Kamu telah menyelesaikan sesi ini. Semoga cramps kamu mereda."
                    else
                        "Lakukan aktivitas ini dengan santai dan atur pernapasan secara perlahan.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFinished) PinkPrimary else Color.LightGray
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = if (isFinished) "Tutup" else "Batal",
                        color = if (isFinished) Color.White else Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
