package ru.vladimir.tilikov.roomdaomessenger.ui.contacts.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.vladimir.tilikov.roomdaomessenger.data.UserAccessState
import ru.vladimir.tilikov.roomdaomessenger.data.models.Contact
import ru.vladimir.tilikov.roomdaomessenger.data.repositories.ContactRepository
import ru.vladimir.tilikov.roomdaomessenger.utils.SingleLiveEvent
import timber.log.Timber

class ContactAddFragmentViewModel: ViewModel() {

    private val repository = ContactRepository()
    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "ContactAddFragmentViewModel | errorHandler")
    }

    private val _addComplete = SingleLiveEvent<Unit>()

    val addComplete: LiveData<Unit> get() = _addComplete

    fun addContact(name: String, avatar: String? = null) {

        val activeUserId = UserAccessState.activeUserId
        val contact = Contact(
            id = 0L,
            userId = activeUserId,
            name = name,
            avatar = avatar
        )

        viewModelScope.launch(errorHandler) {
            try {
                repository.addContacts(contact)
                _addComplete.call()
            } catch (t: Throwable) {
                Timber.e(t, "Error | ContactAddFragmentViewModel | addContact")
            }
        }
    }
}