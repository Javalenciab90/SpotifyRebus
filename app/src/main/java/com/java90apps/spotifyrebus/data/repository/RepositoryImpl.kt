package com.java90apps.spotifyrebus.data.repository

import com.java90apps.spotifyrebus.data.datasource.AudioBoomService
import com.java90apps.spotifyrebus.domain.models.AudioClipModel
import com.java90apps.spotifyrebus.domain.models.ChannelsRecommendedModel
import com.java90apps.spotifyrebus.domain.models.PlayListModel
import com.java90apps.spotifyrebus.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor (
    private val apiService: AudioBoomService
) : Repository {

    override suspend fun getChannelsRecommended() : ChannelsRecommendedModel {
        return apiService.getChannelsRecommended().toChannelRecommendedModel()
    }

    override suspend fun getPlayListChannel(channelId: Int) : List<AudioClipModel> {
        return apiService.getPlayListChannel(channelId).toPlayListModel().body.audio_clips
    }
}