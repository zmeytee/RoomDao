package ru.vladimir.tilikov.roomdaomessenger.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import ru.vladimir.tilikov.roomdaomessenger.data.models.File

@Dao
interface FileDao {

    @Query("SELECT * FROM ${MessengerContract.Files.TABLE_NAME} WHERE ${MessengerContract.Files.USER_ID} = :userId")
    suspend fun getAllByUserId(userId: Long): List<File>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFile(file: File): Long
}