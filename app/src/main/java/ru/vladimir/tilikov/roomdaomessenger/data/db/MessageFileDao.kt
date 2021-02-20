package ru.vladimir.tilikov.roomdaomessenger.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.vladimir.tilikov.roomdaomessenger.data.models.MessageFileCrossRef

@Dao
interface MessageFileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMessageFile(messageFile: MessageFileCrossRef)
}