package ru.vladimir.tilikov.roomdaomessenger.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import ru.vladimir.tilikov.roomdaomessenger.utils.TestData

object Database {

    lateinit var instance: MessengerDatabase
        private set

    fun init(context: Context) {
        instance = Room.databaseBuilder(
            context,
            MessengerDatabase::class.java,
            MessengerDatabase.DB_NAME
        )
            .addMigrations(MIGRATION_1_2)
            .addCallback(dbCallback)
            .build()
    }

    private val dbCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            CoroutineScope(Dispatchers.IO).launch {

                db.execSQL("INSERT INTO ${MessengerContract.Users.TABLE_NAME} VALUES (0, 'admin', 'messenger', 'xxx@xxx.xx', '12214325', 'admin', 'admin', NULL, 0)")

                TestData.imageUriList.forEach {
                    db.execSQL(
                        "INSERT INTO ${MessengerContract.Files.TABLE_NAME} " +
                                "(${MessengerContract.Files.USER_ID}, ${MessengerContract.Files.FILE_URI}) " +
                                "VALUES (0, \'$it\')"
                    )
                }
            }
        }
    }
}