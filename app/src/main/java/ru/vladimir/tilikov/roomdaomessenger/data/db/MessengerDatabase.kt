package ru.vladimir.tilikov.roomdaomessenger.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vladimir.tilikov.roomdaomessenger.data.db.MessengerDatabase.Companion.DB_VERSION
import ru.vladimir.tilikov.roomdaomessenger.data.models.*

@Database(
    entities = [
        Contact::class,
        Message::class,
        MessageStatus::class,
        User::class,
        Address::class,
        File::class,
        MessageFileCrossRef::class
    ],
    version = DB_VERSION
)
abstract class MessengerDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    abstract fun messageDao(): MessageDao

    abstract fun userDao(): UserDao

    abstract fun fileDao(): FileDao

    abstract fun messageFileDao(): MessageFileDao

    companion object {
        const val DB_VERSION = 2
        const val DB_NAME = "messenger-testX"
    }
}