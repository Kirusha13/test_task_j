package com.example.coursesapp.data.repository

import com.example.coursesapp.data.local.dao.FavoriteDao
import com.example.coursesapp.data.local.entity.FavoriteEntity
import com.example.coursesapp.data.mapper.toDomain
import com.example.coursesapp.data.remote.api.CourseApiService
import com.example.coursesapp.data.remote.api.CourseDto
import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val apiService: CourseApiService,
    private val favoriteDao: FavoriteDao
) : CourseRepository {

    private var cachedCourses: List<CourseDto> = emptyList()

    override suspend fun getCourses(): List<Course> {
        val response = apiService.getCourses()
        cachedCourses = response.courses
        val favoriteIds = favoriteDao.getAllFavoriteIdsOnce()
        return response.courses.map { dto ->
            dto.toDomain(isFavorite = favoriteIds.contains(dto.id))
        }
    }

    override fun getFavorites(): Flow<List<Course>> {
        return favoriteDao.getAllFavoriteIds().map { favoriteIds ->
            cachedCourses
                .filter { favoriteIds.contains(it.id) }
                .map { it.toDomain(isFavorite = true) }
        }
    }

    override suspend fun toggleFavorite(courseId: Int) {
        if (favoriteDao.isFavorite(courseId)) {
            favoriteDao.removeFavorite(courseId)
        } else {
            favoriteDao.addFavorite(FavoriteEntity(courseId))
        }
    }

    override suspend fun isFavorite(courseId: Int): Boolean {
        return favoriteDao.isFavorite(courseId)
    }
}
