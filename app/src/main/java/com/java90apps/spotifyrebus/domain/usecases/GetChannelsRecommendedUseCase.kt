package com.java90apps.spotifyrebus.domain.usecases

import com.java90apps.spotifyrebus.domain.models.ChannelsRecommendedModel
import com.java90apps.spotifyrebus.domain.repository.Repository
import javax.inject.Inject

class GetChannelsRecommendedUseCase @Inject constructor (
    private val repository: Repository
) {
    suspend operator fun invoke() : ChannelsRecommendedModel {
        return repository.getChannelsRecommended()
    }
}