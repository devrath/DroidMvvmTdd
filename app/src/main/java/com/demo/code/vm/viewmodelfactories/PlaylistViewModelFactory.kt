package com.demo.code.vm.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.code.repositories.PlaylistRepository
import com.demo.code.vm.viewmodels.PlaylistViewModel

class PlaylistViewModelFactory (
        private val repository : PlaylistRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaylistViewModel(repository) as T
    }
}
