package com.example.coursesapp.domain.usecase

import com.example.coursesapp.domain.repository.CourseRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(courseId: Int) {
        repository.toggleFavorite(courseId)
    }
}
