package ru.vladimir.tilikov.roomdaomessenger.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract

@Entity(
    tableName = MessengerContract.MessageStatus.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Message::class,
            parentColumns = [MessengerContract.Messages.ID],
            childColumns = [MessengerContract.MessageStatus.MESSAGE_ID],
            onDelete = CASCADE
        )
    ]
)
data class MessageStatus(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MessengerContract.MessageStatus.MESSAGE_ID)
    val messageId: Long,
    @ColumnInfo(name = MessengerContract.MessageStatus.STATUS)
    val status: Boolean
)