package com.example.coopdemo2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coopdemo2.api.CatApi
import com.example.coopdemo2.api.model.Breed
import com.example.coopdemo2.api.model.CatPicture
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {
    // each line exposes cold data streams with StateFlow
    // Flows are useful for when data updates over time
    private val _randomCatPicture = MutableStateFlow<String?>(null)
    val randomCatPicture = _randomCatPicture.asStateFlow()

    // whenever the ViewModel updates a value,
    // the composable automatically re-renders the UI
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _breedPictures = MutableStateFlow<List<CatPicture>>(emptyList())
    val breedPictures = _breedPictures.asStateFlow()

    private val _allBreeds = MutableStateFlow<List<Breed>>(emptyList())
    val allBreeds = _allBreeds.asStateFlow()

    init {
        loadCatBreeds()
    }

    fun loadRandomCat() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = CatApi.service.getRandomCatPicture()
                _randomCatPicture.value = result.firstOrNull()?.url
            } catch (_: Exception) {
                _randomCatPicture.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun loadCatBreeds() {
        viewModelScope.launch {
            try {
                val breeds = CatApi.service.getBreeds()
                _allBreeds.value = breeds
            } catch (_: Exception) {
                _allBreeds.value = emptyList()
            }
        }
    }

    fun searchAndLoadBreedPictures(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val breed = _allBreeds.value.find { it.name.equals(query, ignoreCase = true) }
                if (breed == null) {
                    _breedPictures.value = emptyList()

                    // this is called a labeled return
                    // returns from a lambda or specific scope
                    // instead of the entire function
                    return@launch
                }

                val result = CatApi.service.getPicturesByBreed(breed.id, limit = 10)
                _breedPictures.value = result
            } catch (_: Exception) {
                _breedPictures.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}