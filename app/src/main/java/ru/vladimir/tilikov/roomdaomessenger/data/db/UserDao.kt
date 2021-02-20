package ru.vladimir.tilikov.roomdaomessenger.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import ru.vladimir.tilikov.roomdaomessenger.data.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM ${MessengerContract.Users.TABLE_NAME} " +
            "WHERE ${MessengerContract.Users.LOGIN} = :login " +
            "AND ${MessengerContract.Users.PASSWORD_HASH} = :password")
    suspend fun getUserByLoginPassword(login: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveUser(user: User): Long
}