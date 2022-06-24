package com.java90apps.spotifyrebus.domain.repository

import com.java90apps.spotifyrebus.domain.models.ChannelsRecommendedModel
import com.java90apps.spotifyrebus.domain.models.PlayListModel

interface Repository {
    suspend fun getChannelsRecommended() : ChannelsRecommendedModel
    suspend fun getPlayListChannel(channelId: Double) : PlayListModel
}