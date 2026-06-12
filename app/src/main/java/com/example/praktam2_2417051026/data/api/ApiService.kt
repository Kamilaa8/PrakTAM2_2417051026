package com.example.praktam2_2417051026.data.api

import com.example.praktam2_2417051026.data.model.Tracker
import retrofit2.http.GET

interface ApiService {
    @GET("tracker")
    suspend fun getTrackers(): List<Tracker>
}
