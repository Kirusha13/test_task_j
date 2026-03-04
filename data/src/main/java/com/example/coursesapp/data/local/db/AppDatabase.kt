package com.example.coursesapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coursesapp.data.local.dao.FavoriteDao
import com.example.coursesapp.data.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
