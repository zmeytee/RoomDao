package ru.vladimir.tilikov.roomdaomessenger.data.models

import androidx.room.TypeConverter
import java.time.Instant

class IntentConverter {

    @TypeConverter
    fun convertInstantToLong(instant: Instant): Long = instant.toEpochMilli()

    @TypeConverter
    fun convertLongToInstant(epochMilli: Long): Instant = Instant.ofEpochMilli(epochMilli)
}