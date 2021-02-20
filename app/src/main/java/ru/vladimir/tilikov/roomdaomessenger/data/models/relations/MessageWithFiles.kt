package ru.vladimir.tilikov.roomdaomessenger.data.models.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import ru.vladimir.tilikov.roomdaomessenger.data.models.File
import ru.vladimir.tilikov.roomdaomessenger.data.models.Message
import ru.vladimir.tilikov.roomdaomessenger.data.models.MessageFileCrossRef

data class MessageWithFiles(
    @Embedded
    val message: Message,
    @Relation(
        parentColumn = MessengerContract.Messages.ID,
        entityColumn = MessengerContract.Files.ID,
        associateBy = Junction(MessageFileCrossRef::class)
    )
    val files: List<File>
)