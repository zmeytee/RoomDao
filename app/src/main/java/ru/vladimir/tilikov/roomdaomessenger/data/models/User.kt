package ru.vladimir.tilikov.roomdaomessenger.data.models

import androidx.room.*
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import java.time.Instant

@Entity(
    tableName = MessengerContract.Users.TABLE_NAME,
    indices = [
        Index(MessengerContract.Users.LOGIN)
    ]
)
@TypeConverters(IntentConverter::class)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MessengerContract.Users.ID)
    val id: Long,
    @ColumnInfo(name = MessengerContract.Users.FIRST_NAME)
    val firstName: String,
    @ColumnInfo(name = MessengerContract.Users.LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = MessengerContract.Users.EMAIL)
    val email: String,
    @ColumnInfo(name = MessengerContract.Users.PHONE_NUMBER)
    val phoneNumber: String,
    @ColumnInfo(name = MessengerContract.Users.LOGIN)
    val login: String,
    @ColumnInfo(name = MessengerContract.Users.PASSWORD_HASH)
    val passwordHash: String,
    @ColumnInfo(name = MessengerContract.Users.AVATAR)
    val avatar: String? = null,
    @ColumnInfo(name = MessengerContract.Users.CREATED_AT)
    val created_at: Instant
)