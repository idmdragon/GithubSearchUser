package com.idm.github.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idm.github.models.User
import com.idm.github.repository.ApiUserRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

   private val repository: ApiUserRepository = ApiUserRepository()
    private val _listUser = MutableLiveData<ArrayList<User>>()
    val listUser : LiveData<ArrayList<User>> get() = _listUser

    fun searchUser(keyword : String) = viewModelScope.launch{
        repository.searchUser(keyword).let {
            if (it.isSuccessful){
                _listUser.postValue(it.body()?.items)
            }
        }
    }


}