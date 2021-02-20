package ru.vladimir.tilikov.roomdaomessenger.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object UserAccessState {

    private var userAccess = MutableLiveData(AccessState.DENIED)
    private var userId: Long = 0

    val access: LiveData<AccessState>
        get() = userAccess
    val activeUserId: Long
        get() = userId

    fun setAccess(userId: Long, access: AccessState) {
        this.userId = userId
        userAccess.postValue(access)
    }
}