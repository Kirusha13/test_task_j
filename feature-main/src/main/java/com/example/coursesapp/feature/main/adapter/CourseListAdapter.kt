package com.example.coursesapp.feature.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.coursesapp.domain.model.Course
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CourseListAdapter(
    onFavoriteClick: (Int) -> Unit
) : AsyncListDifferDelegationAdapter<Course>(CourseDiffCallback()) {

    init {
        delegatesManager.addDelegate(courseAdapterDelegate(onFavoriteClick))
    }
}

class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}
