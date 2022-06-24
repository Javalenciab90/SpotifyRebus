package com.java90apps.spotifyrebus.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.java90apps.spotifyrebus.domain.base.BaseViewModel
import com.java90apps.spotifyrebus.domain.models.AudioClipModel
import com.java90apps.spotifyrebus.domain.models.CoroutineContextProvider
import com.java90apps.spotifyrebus.domain.models.StateResult
import com.java90apps.spotifyrebus.domain.usecases.GetPlayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ChannelPlayListViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val getPlayListUseCase: GetPlayListUseCase
) : BaseViewModel(coroutineContextProvider) {

    private val _playList = MutableLiveData<StateResult<List<AudioClipModel>>>()
    val playList: LiveData<StateResult<List<AudioClipModel>>> = _playList

    fun getPlayListChannel(channelId: Int) {
        launch {
            _playList.postValue(StateResult.Loading())
            try {
                withContext(coroutineContextProvider.backgroundContext) {
                    val response = getPlayListUseCase.invoke(channelId)
                    _playList.postValue(StateResult.Success(response))
                }
            } catch (exception: Exception) {
                _playList.postValue(StateResult.Failed(exception.message.toString()))
            }
        }
    }
}