package ru.vladimir.tilikov.roomdaomessenger.ui.contacts.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.vladimir.tilikov.roomdaomessenger.data.UserAccessState
import ru.vladimir.tilikov.roomdaomessenger.data.db.ContactDao
import ru.vladimir.tilikov.roomdaomessenger.data.repositories.ContactRepository
import ru.vladimir.tilikov.roomdaomessenger.utils.SingleLiveEvent
import timber.log.Timber

class ContactListFragmentViewModel(contactDao: ContactDao) : ViewModel() {

    private val repository = ContactRepository()
    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "ContactListFragmentViewModel|errorHandler")
    }
    private val _deletingComplete = SingleLiveEvent<Unit>()

    val deletingComplete: LiveData<Unit> get() = _deletingComplete

    val contactsList = contactDao.getUserContactsById(UserAccessState.activeUserId)

    fun deleteContact(id: Long) {
        viewModelScope.launch(errorHandler) {
            try {
                repository.deleteContactById(id)
                _deletingComplete.call()
            } catch (t: Throwable) {
                Timber.e(t, "Error | ContactListFragmentViewModel | deleteContact")
            }
        }

    }
}