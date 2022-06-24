package com.java90apps.spotifyrebus.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlayListModel(
    @Expose
    @SerializedName("body")
    val body: PlayListBodyModel
) {

}

data class PlayListBodyModel(
    @Expose
    @SerializedName("audio_clips")
    val audio_clips: List<AudioClipModel>
)

data class AudioClipModel(
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
    val urls: UrlsModel
)

data class UrlsModel(
    @Expose
    @SerializedName("high_mp3")
    val high_mp3: String,
    @Expose
    @SerializedName("image")
    val image: String
)