package ir.whoisAbel.roompractice.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ir.whoisAbel.roompractice.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateData(user: User)



}