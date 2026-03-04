package com.example.coursesapp.domain.repository

import com.example.coursesapp.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    suspend fun getCourses(): List<Course>
    fun getFavorites(): Flow<List<Course>>
    suspend fun toggleFavorite(courseId: Int)
    suspend fun isFavorite(courseId: Int): Boolean
}
