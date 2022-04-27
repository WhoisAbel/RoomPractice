package ir.whoisAbel.roompractice.user.data

import ir.whoisAbel.roompractice.db.entities.User
import ir.whoisAbel.roompractice.db.UserDatabase

class UserLocalDataSource(private val userDatabase: UserDatabase) {

    suspend fun addUser(user: User) = userDatabase.userDao().addUser(user)

    fun readAllData() = userDatabase.userDao().readAllData()

    suspend fun updateData(user: User) = userDatabase.userDao().updateData(user)

    suspend fun deleteAllUser() = userDatabase.userDao().deleteAllUser()

    suspend fun deleteUser(user: User) = userDatabase.userDao().deleteUser(user)

}