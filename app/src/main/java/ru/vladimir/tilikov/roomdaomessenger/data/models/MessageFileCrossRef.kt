package ru.vladimir.tilikov.roomdaomessenger.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract

@Entity(
    tableName = MessengerContract.MessageFiles.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Message::class,
            parentColumns = [MessengerContract.Messages.ID],
            childColumns = [MessengerContract.MessageFiles.MESSAGE_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = File::class,
            parentColumns = [MessengerContract.Files.ID],
            childColumns = [MessengerContract.MessageFiles.FILE_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = [
        MessengerContract.MessageFiles.MESSAGE_ID,
        MessengerContract.MessageFiles.FILE_ID
    ]
)
data class MessageFileCrossRef(
    @ColumnInfo(name = MessengerContract.MessageFiles.MESSAGE_ID)
    val messageId: Long,
    @ColumnInfo(name = MessengerContract.MessageFiles.FILE_ID)
    val fileId: Long
)