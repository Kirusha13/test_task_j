package com.example.coursesapp.data.mapper

import com.example.coursesapp.data.remote.api.CourseDto
import com.example.coursesapp.domain.model.Course

fun CourseDto.toDomain(isFavorite: Boolean = false): Course {
    return Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = isFavorite,
        publishDate = publishDate
    )
}
