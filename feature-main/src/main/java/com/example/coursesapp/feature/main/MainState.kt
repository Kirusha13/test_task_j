package com.example.coursesapp.feature.main

import com.example.coursesapp.domain.model.Course

data class MainState(
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
