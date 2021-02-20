package ru.vladimir.tilikov.roomdaomessenger.data.models.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import ru.vladimir.tilikov.roomdaomessenger.data.models.File
import ru.vladimir.tilikov.roomdaomessenger.data.models.Message
import ru.vladimir.tilikov.roomdaomessenger.data.models.MessageFileCrossRef

data class FileWithMessages(
    @Embedded
    val file: File,
    @Relation(
        parentColumn = MessengerContract.Files.ID,
        entityColumn = MessengerContract.Messages.ID,
        associateBy = Junction(MessageFileCrossRef::class)
    )
    val messages: List<Message>
)