package ru.vladimir.tilikov.roomdaomessenger.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract

@Entity(
    tableName = MessengerContract.Files.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = [MessengerContract.Users.ID],
            childColumns = [MessengerContract.Files.USER_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class File(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MessengerContract.Files.ID)
    val id: Long,
    @ColumnInfo(name = MessengerContract.Files.USER_ID)
    val userId: Long,
    @ColumnInfo(name = MessengerContract.Files.FILE_URI)
    val fileUri: String
)