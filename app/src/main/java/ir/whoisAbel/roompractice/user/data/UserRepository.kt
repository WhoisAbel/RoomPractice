package ir.whoisAbel.roompractice.user.data

import ir.whoisAbel.roompractice.db.entities.User

class UserRepository(private val userLocalDataSource: UserLocalDataSource) {

    suspend fun addUser(user: User) = userLocalDataSource.addUser(user)

    fun readAllData() = userLocalDataSource.readAllData()
}