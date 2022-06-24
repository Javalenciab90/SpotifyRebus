package com.java90apps.spotifyrebus.domain.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.java90apps.spotifyrebus.domain.models.CoroutineContextProvider
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

open class BaseViewModel @Inject constructor(
    val coroutineContextProvider: CoroutineContextProvider
): ViewModel(), LifecycleEventObserver, CoroutineScope {

    private val parentJob: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = coroutineContextProvider.mainContext + parentJob

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            unBind()
        }
    }

    fun unBind() {
        parentJob.apply {
            cancelChildren()
        }
    }
}