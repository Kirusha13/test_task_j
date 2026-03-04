package com.example.coursesapp.feature.main.adapter

import androidx.core.content.ContextCompat
import com.example.coursesapp.core.R as CoreR
import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.feature.main.databinding.ItemCourseBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun courseAdapterDelegate(
    onFavoriteClick: (Int) -> Unit
) = adapterDelegateViewBinding<Course, Course, ItemCourseBinding>(
    { layoutInflater, parent ->
        ItemCourseBinding.inflate(layoutInflater, parent, false)
    }
) {
    val placeholders = mapOf(
        100 to CoreR.drawable.course_placeholder_1,
        101 to CoreR.drawable.course_placeholder_2,
        102 to CoreR.drawable.course_placeholder_3,
        103 to CoreR.drawable.course_placeholder_4,
        104 to CoreR.drawable.course_placeholder_5
    )

    binding.bookmarkIcon.setOnClickListener {
        onFavoriteClick(item.id)
    }

    bind {
        binding.courseTitle.text = item.title
        binding.courseDescription.text = item.text
        binding.coursePrice.text = context.getString(CoreR.string.currency_format, item.price)
        binding.courseRating.text = item.rate
        binding.courseDate.text = item.startDate
        binding.courseImage.setImageResource(
            placeholders[item.id] ?: CoreR.drawable.course_placeholder_1
        )
        binding.bookmarkIcon.setImageResource(
            if (item.hasLike) CoreR.drawable.ic_bookmark_filled
            else CoreR.drawable.ic_bookmark
        )
        binding.bookmarkIcon.setColorFilter(
            if (item.hasLike) ContextCompat.getColor(context, CoreR.color.primary_green)
            else ContextCompat.getColor(context, CoreR.color.text_hint)
        )
    }
}
