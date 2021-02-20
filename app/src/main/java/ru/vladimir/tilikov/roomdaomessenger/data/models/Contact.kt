package ru.vladimir.tilikov.roomdaomessenger.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract

@Parcelize
@Entity(tableName = MessengerContract.Contacts.TABLE_NAME)
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MessengerContract.Contacts.ID)
    val id: Long,
    @ColumnInfo(name = MessengerContract.Contacts.USER_ID)
    val userId: Long,
    @ColumnInfo(name = MessengerContract.Contacts.NAME)
    val name: String,
    @ColumnInfo(name = MessengerContract.Contacts.AVATAR)
    val avatar: String? = null
): Parcelable