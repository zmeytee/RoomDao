package ru.vladimir.tilikov.roomdaomessenger.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import ru.vladimir.tilikov.roomdaomessenger.data.models.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM ${MessengerContract.Contacts.TABLE_NAME} WHERE ${MessengerContract.Contacts.USER_ID} = :userId")
    fun getUserContactsById(userId: Long): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addContacts(contacts: Contact)

    @Query("DELETE FROM ${MessengerContract.Contacts.TABLE_NAME} WHERE ${MessengerContract.Contacts.ID} = :contactId")
    suspend fun deleteContactById(contactId: Long)
}