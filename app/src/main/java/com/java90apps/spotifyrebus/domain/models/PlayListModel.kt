package com.java90apps.spotifyrebus.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PlayListModel(
    val body: PlayListBodyModel
)

data class PlayListBodyModel(
    val audio_clips: List<AudioClipModel>
)

@Parcelize
data class AudioClipModel(
    val description: String,
    val duration: Double,
    val id: Int,
    val title: String,
    val updated_at: String,
    val urls: UrlsModel
) : Parcelable

@Parcelize
data class UrlsModel(
    val high_mp3: String,
    val image: String
) : Parcelable