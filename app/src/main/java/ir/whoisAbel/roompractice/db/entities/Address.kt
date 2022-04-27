package ir.whoisAbel.roompractice.db.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val street: String
) : Parcelable