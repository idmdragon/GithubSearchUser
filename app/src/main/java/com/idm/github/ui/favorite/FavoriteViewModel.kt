package com.idm.github.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.idm.github.db.UserRoomDatabase
import com.idm.github.models.User
import com.idm.github.repository.FavoriteUserRepository

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

   private var repository : FavoriteUserRepository

    init {
        val userDao = UserRoomDatabase.getInstance(application).userDao()
        repository = FavoriteUserRepository(userDao)
    }

    fun getAllData () : LiveData<List<User>>{
        return repository.getAllData()
    }
}