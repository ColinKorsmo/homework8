package com.example.dinosaurs.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinosaurs.network.Dinosaurs
import com.example.dinosaurs.network.DinosaursApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DinosaursUiState {
    data class Success(val photos: List<Dinosaurs>) : DinosaursUiState
    object Error : DinosaursUiState
    object Loading : DinosaursUiState
}

class DinosaursViewModel : ViewModel () {
    var dinosaursUiState: DinosaursUiState by mutableStateOf(DinosaursUiState.Loading)
        private set


    init {
        getDinosaursPhotos()
    }

    /**
     * Gets Dinosaurs photos information from the Mars API Retrofit service and updates
     * [DinosaursPhoto] [List] [MutableList].
     */
    fun getDinosaursPhotos() {
        viewModelScope.launch {
            dinosaursUiState = try {
                DinosaursUiState.Success(DinosaursApi.retrofitService.getPhotos())
            } catch (e: IOException) {
                DinosaursUiState.Error
            }
        }
    }
}
