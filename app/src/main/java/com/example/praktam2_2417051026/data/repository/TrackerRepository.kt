package com.example.praktam2_2417051026.data.repository

import com.example.praktam2_2417051026.data.api.ApiService
import com.example.praktam2_2417051026.data.model.Tracker

class TrackerRepository(private val apiService: ApiService) {
    suspend fun getTrackers(): List<Tracker> {
        return apiService.getTrackers()
    }
}
