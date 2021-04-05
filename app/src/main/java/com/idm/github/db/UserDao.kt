package com.idm.github.db

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.idm.github.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user_favorite")
    fun getAllUser():LiveData<List<User>>

    @Query("SELECT * FROM user_favorite WHERE login = :nama")
    fun getUserAccount(nama : String) : LiveData<User>

    @Query("SELECT * FROM user_favorite")
    fun cursorGetAll(): Cursor
}