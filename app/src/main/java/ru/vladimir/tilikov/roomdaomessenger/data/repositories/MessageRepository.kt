package ru.vladimir.tilikov.roomdaomessenger.data.repositories

import ru.vladimir.tilikov.roomdaomessenger.data.db.Database
import ru.vladimir.tilikov.roomdaomessenger.data.models.Message
import ru.vladimir.tilikov.roomdaomessenger.data.models.MessageFileCrossRef
import ru.vladimir.tilikov.roomdaomessenger.data.models.MessageStatus

class MessageRepository {

    private val messageDao = Database.instance.messageDao()
    private val messageFileDao = Database.instance.messageFileDao()

    suspend fun addMessage(message: Message): Long {
        return messageDao.addMessageWithStatus(message)
    }

    suspend fun deleteMessageById(messageId: Long) {
        messageDao.deleteMessageById(messageId)
    }

    suspend fun changeMessageStatus(messageId: Long) {
        messageDao.updateMessageStatus(MessageStatus(messageId, true))
    }

    suspend fun addMessageFileCrossRef(messageFile: MessageFileCrossRef) {
        messageFileDao.addMessageFile(messageFile)
    }
}