package com.java90apps.spotifyrebus.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlayListDTO(
    @Expose
    @SerializedName("body")
    val body: PlayListBody
)

data class PlayListBody(
    @Expose
    @SerializedName("audio_clips")
    val audio_clips: List<AudioClip>
)

data class AudioClip(
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("duration")
    val duration: Double,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("updated_at")
    val updated_at: String,
    @Expose
    @SerializedName("urls")
    val urls: Urls
)

data class Urls(
    @Expose
    @SerializedName("high_mp3")
    val high_mp3: String,
    @Expose
    @SerializedName("image")
    val image: String
)
