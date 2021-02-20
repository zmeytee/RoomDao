package ru.vladimir.tilikov.roomdaomessenger.ui.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.vladimir.tilikov.roomdaomessenger.data.db.MessageDao
import ru.vladimir.tilikov.roomdaomessenger.data.models.Message
import ru.vladimir.tilikov.roomdaomessenger.data.repositories.MessageRepository
import ru.vladimir.tilikov.roomdaomessenger.utils.SingleLiveEvent
import timber.log.Timber

class MessagesFragmentViewModel(contactId: Long, messageDao: MessageDao): ViewModel() {

    private val repository = MessageRepository()
    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "MessagesFragmentViewModel errorHandler ${throwable.message}")
    }
    private val _deletingComplete = SingleLiveEvent<Unit>()

    val deletingComplete: LiveData<Unit> get() = _deletingComplete

    val messagesList = messageDao.getAllMessagesByContactId(contactId)

    fun addMessage(message: Message) {
        viewModelScope.launch(errorHandler) {
            try {
                repository.addMessage(message)
            } catch (t: Throwable) {
                Timber.e(t, "Error: MessagesFragmentViewModel: addMessage ${t.message}")
            }
        }
    }

    fun deleteMessage(id: Long) {
        viewModelScope.launch(errorHandler) {
            try {
                repository.deleteMessageById(id)
                _deletingComplete.call()
            } catch (t: Throwable) {
                Timber.e(t, "Error: MessagesFragmentViewModel: deleteMessage ${t.message}")
            }
        }
    }

    fun changeMessageStatus(messageId: Long) {
        viewModelScope.launch(errorHandler) {
            try {
                repository.changeMessageStatus(messageId)
            } catch (t: Throwable) {
                Timber.e(t, "Error: MessagesFragmentViewModel: changeMessageStatus ${t.message}")
            }
        }
    }
}