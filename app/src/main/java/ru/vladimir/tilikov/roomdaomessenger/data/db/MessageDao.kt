package ru.vladimir.tilikov.roomdaomessenger.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import ru.vladimir.tilikov.roomdaomessenger.data.models.Message
import ru.vladimir.tilikov.roomdaomessenger.data.models.MessageStatus
import ru.vladimir.tilikov.roomdaomessenger.data.models.relations.MessageWithStatus

@Dao
interface MessageDao {

    @Transaction
    @Query("SELECT * FROM ${MessengerContract.Messages.TABLE_NAME} WHERE ${MessengerContract.Messages.CONTACT_ID} = :contactId")
    fun getAllMessagesByContactId(contactId: Long): LiveData<List<MessageWithStatus>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addMessage(message: Message): Long

    @Query("DELETE FROM ${MessengerContract.Messages.TABLE_NAME} WHERE ${MessengerContract.Messages.ID} = :messageId")
    suspend fun deleteMessageById(messageId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMessageStatus(messageStatus: MessageStatus)

    @Update
    suspend fun updateMessageStatus(messageStatus: MessageStatus)

    @Transaction
    suspend fun addMessageWithStatus(message: Message, defaultStatus: Boolean = false): Long {
        val id = addMessage(message)
        addMessageStatus(MessageStatus(id, defaultStatus))
        return id
    }
}