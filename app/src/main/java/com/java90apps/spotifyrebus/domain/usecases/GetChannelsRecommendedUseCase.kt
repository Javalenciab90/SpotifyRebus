package com.java90apps.spotifyrebus.domain.usecases

import com.java90apps.spotifyrebus.domain.repository.Repository
import javax.inject.Inject

class GetChannelsRecommendedUseCase @Inject constructor (
    private val repository: Repository
) {

}