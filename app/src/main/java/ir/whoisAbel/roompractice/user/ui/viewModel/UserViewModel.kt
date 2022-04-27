package ir.whoisAbel.roompractice.user.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ir.whoisAbel.roompractice.db.entities.User
import ir.whoisAbel.roompractice.user.data.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val users = liveData {
        emitSource(userRepository.readAllData())
    }


    fun addUserA(user: User) = CoroutineScope(Dispatchers.IO).launch {
        userRepository.addUser(user)
    }

    fun updateData(user: User) = CoroutineScope(Dispatchers.IO).launch {
        userRepository.updateData(user)
    }

    fun deleteUser(user: User) = CoroutineScope(Dispatchers.IO).launch {
        userRepository.deleteUser(user)
    }

    fun deleteAllUser() = CoroutineScope(Dispatchers.IO).launch {
        userRepository.deleteAllUser()
    }
}