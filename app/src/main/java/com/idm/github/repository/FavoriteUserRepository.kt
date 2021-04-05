package com.idm.github.repository

import com.idm.github.db.UserDao
import com.idm.github.models.User

class FavoriteUserRepository(private val dao: UserDao) {

    suspend fun upsert(user: User) = dao.upsert(user)

    suspend fun delete(user : User) = dao.delete(user)

    fun getAllData() = dao.getAllUser()

    fun getUserAccount(username : String) = dao.getUserAccount(username)

}