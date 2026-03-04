package com.example.coursesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coursesapp.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT courseId FROM favorites")
    fun getAllFavoriteIds(): Flow<List<Int>>

    @Query("SELECT courseId FROM favorites")
    suspend fun getAllFavoriteIdsOnce(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE courseId = :courseId")
    suspend fun removeFavorite(courseId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE courseId = :courseId)")
    suspend fun isFavorite(courseId: Int): Boolean
}
