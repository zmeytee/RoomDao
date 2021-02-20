package ru.vladimir.tilikov.roomdaomessenger.data.repositories

import ru.vladimir.tilikov.roomdaomessenger.data.db.Database
import ru.vladimir.tilikov.roomdaomessenger.data.models.Contact

class ContactRepository {

    private val contactDao = Database.instance.contactDao()

    suspend fun addContacts(contact: Contact) {
        contactDao.addContacts(contact)
    }

    suspend fun deleteContactById(contactId: Long) {
        contactDao.deleteContactById(contactId)
    }
}