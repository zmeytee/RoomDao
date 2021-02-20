package ru.vladimir.tilikov.roomdaomessenger.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract

@Entity(tableName = MessengerContract.Addresses.TABLE_NAME)
data class Address(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MessengerContract.Addresses.ID)
    val id: Long,
    @ColumnInfo(name = MessengerContract.Addresses.USER_ID)
    val userId: Long,
    @ColumnInfo(name = MessengerContract.Addresses.COUNTRY)
    val country: String,
    @ColumnInfo(name = MessengerContract.Addresses.CITY)
    val city: String,
    @ColumnInfo(name = MessengerContract.Addresses.STREET)
    val street: String
)