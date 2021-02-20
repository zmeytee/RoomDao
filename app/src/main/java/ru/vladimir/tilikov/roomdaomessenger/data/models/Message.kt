package ru.vladimir.tilikov.roomdaomessenger.data.models

import androidx.room.*
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import java.time.Instant

@Entity(
    tableName = MessengerContract.Messages.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Contact::class,
            parentColumns = [MessengerContract.Contacts.ID],
            childColumns = [MessengerContract.Messages.CONTACT_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@TypeConverters(IntentConverter::class)
data class Message(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MessengerContract.Messages.ID)
    val id: Long,
    @ColumnInfo(name = MessengerContract.Messages.CONTACT_ID)
    val contactId: Long,
    @ColumnInfo(name = MessengerContract.Messages.MESSAGE)
    val message: String,
    @ColumnInfo(name = MessengerContract.Messages.CREATED_AT)
    val createdAt: Instant
)
