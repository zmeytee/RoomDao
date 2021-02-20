package ru.vladimir.tilikov.roomdaomessenger.app

import android.app.Application
import ru.vladimir.tilikov.roomdaomessenger.data.db.Database
import timber.log.Timber

class RoomDaoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Database.init(this)

//        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(
//                StrictMode.ThreadPolicy.Builder()
//                    .detectAll()
//                    .penaltyDeath()
//                    .build()
//            )
//        }
    }
}
