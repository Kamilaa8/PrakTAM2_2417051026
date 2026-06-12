package com.example.praktam2_2417051026.data.model

import com.google.gson.annotations.SerializedName

data class Tracker(
    @SerializedName("skala")
    val skala: String,

    @SerializedName("deskripsi")
    val deskripsi: String,

    @SerializedName(value = "image_url", alternate = ["imageurl"])
    val imageUrl: String
)
