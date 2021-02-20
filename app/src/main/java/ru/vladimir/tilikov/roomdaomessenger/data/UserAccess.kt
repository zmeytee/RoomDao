package ru.vladimir.tilikov.roomdaomessenger.data

data class UserAccess(
    val userId: Long?,
    val state: AccessState = AccessState.DENIED
)

enum class AccessState {
    SUCCESS,
    DENIED
}