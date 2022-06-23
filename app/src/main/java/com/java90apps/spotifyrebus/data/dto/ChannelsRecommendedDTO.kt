package com.java90apps.spotifyrebus.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChannelsRecommendedDTO(
    @Expose
    @SerializedName("api_warning")
    val api_warning: String,
    @Expose
    @SerializedName("body")
    val body: List<Body>
)

data class Body(
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
    val urls: Urls
)

data class Urls(
    @Expose
    @SerializedName("logo_image")
    val logo_image: LogoImage
)

data class LogoImage(
    @Expose
    @SerializedName("original")
    val original: String
)

