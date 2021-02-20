package ru.vladimir.tilikov.roomdaomessenger.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import timber.log.Timber

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        Timber.d("migration 1-2 start")
        database.execSQL("ALTER TABLE Users ADD COLUMN created_at INTEGER NOT NULL DEFAULT 0")
        Timber.d("migration 1-2 success")
    }

}