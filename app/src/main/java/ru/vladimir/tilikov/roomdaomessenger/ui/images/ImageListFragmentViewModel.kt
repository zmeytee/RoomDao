package ru.vladimir.tilikov.roomdaomessenger.ui.images

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.vladimir.tilikov.roomdaomessenger.data.UserAccessState
import ru.vladimir.tilikov.roomdaomessenger.data.db.Database
import ru.vladimir.tilikov.roomdaomessenger.data.entities.Image
import ru.vladimir.tilikov.roomdaomessenger.data.models.File
import ru.vladimir.tilikov.roomdaomessenger.data.models.Message
import ru.vladimir.tilikov.roomdaomessenger.data.models.MessageFileCrossRef
import ru.vladimir.tilikov.roomdaomessenger.data.repositories.ImageRepository
import ru.vladimir.tilikov.roomdaomessenger.data.repositories.MessageRepository
import ru.vladimir.tilikov.roomdaomessenger.utils.SingleLiveEvent
import timber.log.Timber
import java.time.Instant

class ImageListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val messageRepository = MessageRepository()
    private val imageRepository = ImageRepository()
    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "Error | ImageListFragmentViewModel | errorHandler ${throwable.message}")
    }

    private val _images = MutableLiveData<List<Image>>()
    private val _addMessageComplete = SingleLiveEvent<Unit>()

    val images: LiveData<List<Image>>
        get() = _images
    val addMessageComplete: LiveData<Unit>
        get() = _addMessageComplete

    fun getAllImages() {
        viewModelScope.launch(errorHandler) {
            try {
                val list = imageRepository.getAllFiles(0)
                    .map { Image("", it.fileUri) }
                _images.value = list
            } catch (t: Throwable) {
                Timber.e(t, "Error | ImageListFragmentViewModel | getAllImages -> ${t.message}")
            }
        }
    }

    fun addImageMessage(contactId: Long, imageUri: String) {
        viewModelScope.launch(errorHandler) {
            val message = Message(
                id = 0,
                contactId = contactId,
                message = "",
                createdAt = Instant.now()
            )

            val file = File(
                id = 0,
                userId = UserAccessState.activeUserId,
                fileUri = imageUri
            )

            try {
                Database.instance.withTransaction {
                    val imageId = imageRepository.addImage(file)
                    val messageId = messageRepository.addMessage(message)
                    messageRepository.addMessageFileCrossRef(
                        MessageFileCrossRef(messageId, imageId)
                    )
                }
                _addMessageComplete.call()
            } catch (t: Throwable) {
                Timber.e(t, "Error | ImageListFragmentViewModel | addImageMessage -> ${t.message}")
            }
        }
    }

    fun addImage(userId: Long, fileUri: String) {
        viewModelScope.launch(errorHandler) {
            try {
                imageRepository.addImage(
                    File(
                        id = 0,
                        userId = userId,
                        fileUri = fileUri
                    )
                )
            } catch (t: Throwable) {
                Timber.e(t, "Error: ImageListFragmentViewModel: addImage -> ${t.message}")
            }
        }
    }
}