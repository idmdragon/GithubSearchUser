package com.idm.github.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.idm.github.db.UserRoomDatabase
import com.idm.github.models.User
import com.idm.github.repository.ApiUserRepository
import com.idm.github.repository.FavoriteUserRepository
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val apiRepository: ApiUserRepository = ApiUserRepository()
    private val dbRepository: FavoriteUserRepository

    init {
        val userDao = UserRoomDatabase.getInstance(application).userDao()
        dbRepository = FavoriteUserRepository(userDao)
    }

    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user

    private val _listFollowers = MutableLiveData<ArrayList<User>>()
    val listFollowers : MutableLiveData<ArrayList<User>>  = _listFollowers

    private val _listFollowing = MutableLiveData<ArrayList<User>>()
    val listFollowing : MutableLiveData<ArrayList<User>>  = _listFollowing


    fun getDetailUser(keyword : String) = viewModelScope.launch{
        apiRepository.getDetailUser(keyword).let {
            if (it.isSuccessful){
                _user.postValue(it.body())
            }
        }
    }


    fun getFollowersUser(keyword: String) = viewModelScope.launch{
        apiRepository.getFollowersUser(keyword).let {
            if (it.isSuccessful){
                _listFollowers.postValue(it.body())
            }
        }
    }

    fun getFollowingUser(keyword: String) = viewModelScope.launch{
        apiRepository.getFollowingUser(keyword).let {
            if (it.isSuccessful){
                _listFollowing.postValue(it.body())
            }
        }
    }


    fun upsert() = viewModelScope.launch {
        user.value?.let {
            dbRepository.upsert(it)
        }

    }
    fun delete() = viewModelScope.launch {
        user.value?.let {
            dbRepository.delete(it)
        }

    }

    fun getUserAccount(username : String) = dbRepository.getUserAccount(username)

}