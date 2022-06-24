package com.java90apps.spotifyrebus.domain.models

data class ChannelsRecommendedModel(
    val body: List<ChannelModel>
)

data class ChannelModel(
    val description: String,
    val id: Int,
    val title: String,
    val updated_at: String,
    val urls: ChannelUrlsModel
)

data class ChannelUrlsModel(
    val logo_image: LogoImageModel
)

data class LogoImageModel(
    val original: String
)