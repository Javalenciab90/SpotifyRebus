package com.java90apps.spotifyrebus.di

import com.java90apps.spotifyrebus.data.repository.RepositoryImpl
import com.java90apps.spotifyrebus.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ) : Repository
}