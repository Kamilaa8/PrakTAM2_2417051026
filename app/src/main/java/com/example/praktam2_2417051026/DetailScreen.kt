package com.example.praktam2_2417051026

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.platform.LocalContext
import com.example.praktam2_2417051026.Model.Tracker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    tracker: Tracker,
    onBack: () -> Unit,
    onSave: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val imageResId = remember(tracker.imageName) {
        val resId = context.resources.getIdentifier(tracker.imageName, "drawable", context.packageName)
        if (resId != 0) resId else R.drawable.emoji2
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = imageResId,
            contentDescription = "Skala ${tracker.skala}",
            placeholder = painterResource(id = R.drawable.menstrual),
            error = painterResource(id = R.drawable.emoji2),
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = tracker.deskripsi, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Skala Nyeri: ${tracker.skala}")
        Spacer(modifier = Modifier.height(20.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        Button(
            enabled = !isLoading,
            onClick = {
                scope.launch {
                    isLoading = true
                    delay(2000)
                    isLoading = false
                    snackbarHostState.showSnackbar("Catatan berhasil disimpan")
                    onSave()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Catatan")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Kembali")
        }
    }
}
