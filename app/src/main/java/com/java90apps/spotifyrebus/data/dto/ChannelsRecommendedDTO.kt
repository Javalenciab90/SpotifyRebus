package com.java90apps.spotifyrebus.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.java90apps.spotifyrebus.domain.models.ChannelModel
import com.java90apps.spotifyrebus.domain.models.ChannelUrlsModel
import com.java90apps.spotifyrebus.domain.models.ChannelsRecommendedModel
import com.java90apps.spotifyrebus.domain.models.LogoImageModel

data class ChannelsRecommendedDTO(
    @Expose
    @SerializedName("body")
    val body: List<ChannelDTO>
) {
    fun toChannelRecommendedModel() : ChannelsRecommendedModel {
        return ChannelsRecommendedModel(
            body = body.map { it.toChannelModel() }
        )
    }
}

data class ChannelDTO(
    @Expose
    @SerializedName("description")
    val description: String,
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
    val urls: ChannelUrlsDTO
) {
    fun toChannelModel() : ChannelModel {
        return ChannelModel(
            description = description,
            id = id,
            title = title,
            updated_at = updated_at,
            urls = urls.toChannelUrlsModel()
        )
    }
}

data class ChannelUrlsDTO(
    @Expose
    @SerializedName("logo_image")
    val logo_image: LogoImageDTO
) {
    fun toChannelUrlsModel() :  ChannelUrlsModel {
        return ChannelUrlsModel(
            logo_image = logo_image.toLogoImageModel()
        )
    }
}

data class LogoImageDTO(
    @Expose
    @SerializedName("original")
    val original: String
) {
   fun toLogoImageModel() : LogoImageModel {
        return LogoImageModel(
            original = original
        )
   }
}

