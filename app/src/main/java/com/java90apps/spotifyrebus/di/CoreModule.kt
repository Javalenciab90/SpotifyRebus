package com.java90apps.spotifyrebus.di

import com.java90apps.spotifyrebus.domain.models.CoroutineContextProvider
import com.java90apps.spotifyrebus.domain.repository.Repository
import com.java90apps.spotifyrebus.domain.usecases.GetChannelsRecommendedUseCase
import com.java90apps.spotifyrebus.domain.usecases.GetPlayListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideCoroutineContextProvider() : CoroutineContextProvider {
        return CoroutineContextProvider(
            Dispatchers.Main,
            Dispatchers.IO
        )
    }

    @Singleton
    @Provides
    fun provideGetChannelsRecommendedUseCase(repository: Repository) : GetChannelsRecommendedUseCase {
        return GetChannelsRecommendedUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetPlayListUseCase(repository: Repository) : GetPlayListUseCase {
        return GetPlayListUseCase(repository)
    }
}