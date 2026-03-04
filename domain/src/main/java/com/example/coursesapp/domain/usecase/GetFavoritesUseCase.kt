package com.example.coursesapp.domain.usecase

import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    operator fun invoke(): Flow<List<Course>> {
        return repository.getFavorites()
    }
}
