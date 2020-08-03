package com.example.android.radiancex.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.android.radiancex.model.User
import com.example.android.radiancex.repository.UserRepository
import kotlinx.coroutines.*

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: UserRepository = UserRepository()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val currentUser : LiveData<User>? = repository.getCurrentUser()

    lateinit var allUsers: LiveData<List<User>>

    fun getUser(userID: String): LiveData<User> {
        return repository.getUser(userID)
    }

    fun getCurrentLoggedInUser(): LiveData<User>? {
        return repository.getCurrentUser()
    }

    fun getAllUsers(userID: String): LiveData<List<User>> {
        return repository.getAllUsers()
    }
}