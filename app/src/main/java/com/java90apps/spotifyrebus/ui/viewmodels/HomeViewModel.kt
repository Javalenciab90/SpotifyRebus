package com.java90apps.spotifyrebus.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.java90apps.spotifyrebus.domain.base.BaseViewModel
import com.java90apps.spotifyrebus.domain.models.ChannelsRecommendedModel
import com.java90apps.spotifyrebus.domain.models.CoroutineContextProvider
import com.java90apps.spotifyrebus.domain.models.StateResult
import com.java90apps.spotifyrebus.domain.usecases.GetChannelsRecommendedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HomeViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val getChannelsRecommendedUseCase: GetChannelsRecommendedUseCase
) : BaseViewModel(coroutineContextProvider) {

    private val _channels = MutableLiveData<StateResult<ChannelsRecommendedModel>>()
    val channels: LiveData<StateResult<ChannelsRecommendedModel>> = _channels

    init {
        getChannelsRecommended()
    }

    fun getChannelsRecommended() {
        launch {
            _channels.postValue(StateResult.Loading())
            try {
                withContext(coroutineContextProvider.backgroundContext) {
                    val response = getChannelsRecommendedUseCase.invoke()
                    _channels.postValue(StateResult.Success(response))
                }
            } catch (exception: Exception) {
                _channels.postValue(StateResult.Failed(exception.message.toString()))
            }
        }
    }
}