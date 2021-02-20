package ru.vladimir.tilikov.roomdaomessenger.data.repositories

import ru.vladimir.tilikov.roomdaomessenger.data.db.Database
import ru.vladimir.tilikov.roomdaomessenger.data.models.File

class ImageRepository {

    private val fileDao = Database.instance.fileDao()

    suspend fun getAllFiles(userId: Long): List<File> {
        return fileDao.getAllByUserId(userId)
    }

    suspend fun addImage(file: File): Long {
        return fileDao.saveFile(file)
    }
}