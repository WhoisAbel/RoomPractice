package ir.whoisAbel.roompractice.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val lastName: String,
    val age: Int
) : Parcelable