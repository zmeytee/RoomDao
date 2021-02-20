package ru.vladimir.tilikov.roomdaomessenger.data.models.relations

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.MessengerContract
import ru.vladimir.tilikov.roomdaomessenger.data.models.Address
import ru.vladimir.tilikov.roomdaomessenger.data.models.User

data class UserWithAddress(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = MessengerContract.Users.ID,
        entityColumn = MessengerContract.Addresses.USER_ID
    )
    val address: Address,
    @ColumnInfo(name = MessengerContract.Users.AVATAR)
    val avatar: String? = null
)
