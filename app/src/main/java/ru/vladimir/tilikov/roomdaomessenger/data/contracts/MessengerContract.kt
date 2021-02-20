package ru.vladimir.tilikov.roomdaomessenger.data.contracts

object MessengerContract {

    object Addresses {
        const val TABLE_NAME = "Addresses"
        const val ID = "id"
        const val USER_ID = "user_id"
        const val COUNTRY = "country"
        const val CITY = "city"
        const val STREET = "street"
    }


    object Contacts {
        const val TABLE_NAME = "Contacts"
        const val ID = "id"
        const val USER_ID = "user_id"
        const val NAME = "name"
        const val AVATAR = "avatar"
    }

    object Messages {
        const val TABLE_NAME = "Messages"
        const val ID = "own_message_id"
        const val CONTACT_ID = "contact_id"
        const val MESSAGE = "message"
        const val CREATED_AT = "created_at"
    }

    object MessageStatus {
        const val TABLE_NAME = "MessageStatus"
        const val MESSAGE_ID = "message_id"
        const val STATUS = "status"
    }

    object Users {
        const val TABLE_NAME = "Users"
        const val ID = "id"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val EMAIL = "email"
        const val PHONE_NUMBER = "phone_number"
        const val AVATAR = "avatar"
        const val LOGIN = "login"
        const val PASSWORD_HASH = "password_hash"
        const val CREATED_AT = "created_at"
    }

    object MessageFiles {
        const val TABLE_NAME = "MessageFiles"
        const val MESSAGE_ID = "own_message_id"
        const val FILE_ID = "own_file_id"
    }

    object Files {
        const val TABLE_NAME = "Files"
        const val ID = "own_file_id"
        const val USER_ID = "user_id"
        const val FILE_URI = "file_uri"
    }
}