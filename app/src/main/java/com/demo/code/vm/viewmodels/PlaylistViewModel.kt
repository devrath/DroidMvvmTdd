package com.demo.code.vm.viewmodels

import androidx.lifecycle.*
import com.demo.code.repositories.PlaylistRepository
import com.demo.code.models.PlaylistItem
import kotlinx.coroutines.*

class PlaylistViewModel(
    private val repository : PlaylistRepository
) : ViewModel(){

    val progressBarVisibility = MutableLiveData<Boolean>()

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /**
     * Heavy operation that cannot be done in the Main Thread
     */
    fun launchDataLoad() {
        uiScope.launch {
            //sortList() // happens on the background
            // Modify UI
        }
    }

    /** Move the execution off the main thread
     * using withContext(Dispatchers.Default) **/
    suspend fun sortList() = withContext(Dispatchers.Default) {
        // Heavy work
    }

    /** Live data of the playlist **/
    val playList = liveData<Result<List<PlaylistItem>>>{
        // Show the progress
        progressBarVisibility.postValue(true)
        // Live data for the loader is emitted
        emitSource(repository.getPlaylists().asLiveData())
        // Hide the progress
        progressBarVisibility.postValue(false)
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}