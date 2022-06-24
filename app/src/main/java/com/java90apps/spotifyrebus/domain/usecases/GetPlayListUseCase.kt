package com.java90apps.spotifyrebus.domain.usecases

import com.java90apps.spotifyrebus.domain.models.PlayListModel
import com.java90apps.spotifyrebus.domain.repository.Repository
import javax.inject.Inject

class GetPlayListUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(channelId: Double) : PlayListModel {
        return repository.getPlayListChannel(channelId)
    }
}