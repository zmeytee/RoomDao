package ru.vladimir.tilikov.roomdaomessenger.ui.registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.SharedPrefsContract
import ru.vladimir.tilikov.roomdaomessenger.data.models.User
import ru.vladimir.tilikov.roomdaomessenger.data.repositories.AuthorisationRepository
import ru.vladimir.tilikov.roomdaomessenger.data.repositories.SharedPrefsRepository
import ru.vladimir.tilikov.roomdaomessenger.utils.SingleLiveEvent
import timber.log.Timber

class RegistrationDialogViewModel(application: Application) : AndroidViewModel(application) {

    private val loginKey = SharedPrefsContract.LoginForms.LOGIN
    private val passwordKey = SharedPrefsContract.LoginForms.PASSWORD

    private val repository = AuthorisationRepository()
    private val sharedPrefsRepository =
        SharedPrefsRepository(application, SharedPrefsContract.LoginForms.NAME)
    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "LoginFragmentViewModel errorHandler")
    }

    private val _saveSuccess = SingleLiveEvent<Unit>()

    val saveSuccess: LiveData<Unit> get() = _saveSuccess

    fun saveUser(user: User) {
        viewModelScope.launch(errorHandler) {
            try {
                repository.saveUser(user)
                sharedPrefsRepository.editSharedPrefs(loginKey, user.login)
                sharedPrefsRepository.editSharedPrefs(passwordKey, user.passwordHash)
                _saveSuccess.call()
            } catch (t: Throwable) {
                Timber.e(t, "Error RegistrationDialogViewModel: saveUser")
            }
        }
    }
}