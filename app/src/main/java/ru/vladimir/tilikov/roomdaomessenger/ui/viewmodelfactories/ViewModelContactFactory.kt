package ru.vladimir.tilikov.roomdaomessenger.ui.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.vladimir.tilikov.roomdaomessenger.data.db.ContactDao
import ru.vladimir.tilikov.roomdaomessenger.ui.contacts.list.ContactListFragmentViewModel

class ViewModelContactFactory(private val contactDao: ContactDao): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactListFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactListFragmentViewModel(contactDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}