package ru.vladimir.tilikov.roomdaomessenger.data.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import ru.vladimir.tilikov.roomdaomessenger.data.models.MessageStatus

data class MessageWithStatus(
    @Embedded
    val messageWithFiles: MessageWithFiles,
    @Relation(
        entity = MessageStatus::class,
        parentColumn = MessengerContract.Messages.ID,
        entityColumn = MessengerContract.MessageStatus.MESSAGE_ID
    )
    val messageStatus: MessageStatus
)