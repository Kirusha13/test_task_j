package com.example.coursesapp.domain.usecase

import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.domain.repository.CourseRepository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(): List<Course> {
        return repository.getCourses()
    }
}
