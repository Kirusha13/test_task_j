package com.example.coursesapp.data.remote.api

import retrofit2.http.GET

interface CourseApiService {

    @GET("courses")
    suspend fun getCourses(): CourseResponse
}
