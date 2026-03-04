package com.example.coursesapp.feature.favorites.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.coursesapp.domain.model.Course
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FavoritesListAdapter(
    onFavoriteClick: (Int) -> Unit
) : AsyncListDifferDelegationAdapter<Course>(FavoritesDiffCallback()) {

    init {
        delegatesManager.addDelegate(favoriteCourseAdapterDelegate(onFavoriteClick))
    }
}

class FavoritesDiffCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}
