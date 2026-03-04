package com.example.coursesapp.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.domain.usecase.GetFavoritesUseCase
import com.example.coursesapp.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Course>>(emptyList())
    val favorites: StateFlow<List<Course>> = _favorites.asStateFlow()

    init {
        viewModelScope.launch {
            getFavoritesUseCase().collect { courses ->
                _favorites.value = courses
            }
        }
    }

    fun onFavoriteClick(courseId: Int) {
        viewModelScope.launch {
            toggleFavoriteUseCase(courseId)
        }
    }
}
