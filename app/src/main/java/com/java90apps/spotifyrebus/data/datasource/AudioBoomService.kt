package com.java90apps.spotifyrebus.data.datasource

import com.java90apps.spotifyrebus.data.dto.ChannelsRecommendedDTO
import com.java90apps.spotifyrebus.data.dto.PlayListDTO
import retrofit2.http.GET
import retrofit2.http.Path

const val PATH_CHANNEL_ID = "{channel_id}"
const val CHANNELS_RECOMMENDED_URL = "channels/recommended"
const val PLAY_LIST_CHANNEL_URL = "channels/$PATH_CHANNEL_ID/audio_clips"

interface AudioBoomService {

    @GET(CHANNELS_RECOMMENDED_URL)
    suspend fun getChannelsRecommended() : ChannelsRecommendedDTO

    @GET(PLAY_LIST_CHANNEL_URL)
    suspend fun getPlayListChannel(
        @Path(PATH_CHANNEL_ID) channel_id: Double
    ) : PlayListDTO
}