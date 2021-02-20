package ru.vladimir.tilikov.roomdaomessenger.data.repositories

import ru.vladimir.tilikov.roomdaomessenger.data.db.Database
import ru.vladimir.tilikov.roomdaomessenger.data.models.User

class AuthorisationRepository {

    private val userDao = Database.instance.userDao()

    suspend fun getUserByLoginPassword(login: String, password: String): User? {
        return userDao.getUserByLoginPassword(login, password)
    }

    suspend fun saveUser(user: User): Long {
        return userDao.saveUser(user)
    }
}