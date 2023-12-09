package com.skillbox.homework.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.homework.domain.GetDataUseCase
import com.skillbox.homework.entity.MarsPhotos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

const val type = "FHAZ"

class MainViewModel @Inject constructor(private val useCase: GetDataUseCase) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    val filterEnabled = MutableStateFlow(false)

    private val _photos = MutableStateFlow<List<MarsPhotos>>(emptyList())
    val photos: StateFlow<List<MarsPhotos>> =
        combine(_photos, filterEnabled) { photos, filterEnabled ->
            if (filterEnabled) photos.filter { photo ->
                photo.camera?.name.let {
                    it == type
                }
            } else photos
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = _photos.value
        )

    init {
        loadNasaData()
    }

    private fun loadNasaData() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                _photos.value = useCase.execute().photos
                _isLoading.value = false
            }
        }
    }

    fun refresh() {
        loadNasaData()
    }
}