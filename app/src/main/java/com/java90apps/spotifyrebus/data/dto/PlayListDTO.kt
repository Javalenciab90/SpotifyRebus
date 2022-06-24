package com.java90apps.spotifyrebus.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.java90apps.spotifyrebus.domain.models.AudioClipModel
import com.java90apps.spotifyrebus.domain.models.PlayListBodyModel
import com.java90apps.spotifyrebus.domain.models.PlayListModel
import com.java90apps.spotifyrebus.domain.models.UrlsModel

data class PlayListDTO(
    @Expose
    @SerializedName("body")
    val body: PlayListBodyDTO
) {
    fun toPlayListModel() : PlayListModel {
        return PlayListModel(
            body = body.toPlayListBodyModel()
        )
    }
}

data class PlayListBodyDTO(
    @Expose
    @SerializedName("audio_clips")
    val audio_clips: List<AudioClipDTO>
) {
    fun toPlayListBodyModel() : PlayListBodyModel {
        return PlayListBodyModel(
            audio_clips = audio_clips.map { it.toAudioClipModel() }
        )
    }
}

data class AudioClipDTO(
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
    val urls: UrlsDTO
) {
    fun toAudioClipModel() : AudioClipModel {
        return AudioClipModel(
            description = description,
            duration = duration,
            id = id,
            title = title,
            updated_at = updated_at,
            urls = urls.toUrlsModel()
        )
    }
}

data class UrlsDTO(
    @Expose
    @SerializedName("high_mp3")
    val high_mp3: String,
    @Expose
    @SerializedName("image")
    val image: String
) {
    fun toUrlsModel() : UrlsModel {
        return UrlsModel(
            high_mp3 = high_mp3,
            image = image
        )
    }
}
