package com.java90apps.spotifyrebus.domain.repository

import com.java90apps.spotifyrebus.domain.models.AudioClipModel
import com.java90apps.spotifyrebus.domain.models.ChannelsRecommendedModel

interface Repository {
    suspend fun getChannelsRecommended() : ChannelsRecommendedModel
    suspend fun getPlayListChannel(channelId: Int) : List<AudioClipModel>
}