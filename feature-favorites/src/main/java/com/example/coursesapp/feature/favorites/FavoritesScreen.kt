package com.example.coursesapp.feature.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coursesapp.core.ui.components.CourseCard
import com.example.coursesapp.core.ui.theme.BackgroundDark
import com.example.coursesapp.core.ui.theme.TextPrimary
import com.example.coursesapp.core.ui.theme.TextSecondary

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {
    val courses by viewModel.favorites.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        Text(
            text = "Избранное",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 12.dp)
        )

        if (courses.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Нет избранных курсов",
                    fontSize = 16.sp,
                    color = TextSecondary
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(courses, key = { it.id }) { course ->
                    CourseCard(
                        id = course.id,
                        title = course.title,
                        description = course.text,
                        price = course.price,
                        rating = course.rate,
                        date = course.startDate,
                        hasLike = course.hasLike,
                        onFavoriteClick = { viewModel.onFavoriteClick(course.id) }
                    )
                }
            }
        }
    }
}
