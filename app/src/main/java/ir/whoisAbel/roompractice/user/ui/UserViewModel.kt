package ir.whoisAbel.roompractice.user.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.whoisAbel.roompractice.db.entities.User
import ir.whoisAbel.roompractice.user.data.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _readAllData = MutableLiveData<List<User>>()
    val readAllData: LiveData<List<User>>
        get() = _readAllData

    private fun readAllData() {
        _readAllData.postValue(userRepository.readAllData().value)
    }

    fun addUserA(user: User) = CoroutineScope(Dispatchers.IO).launch {
        userRepository.addUser(user)
    }
}