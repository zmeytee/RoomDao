package ru.vladimir.tilikov.roomdaomessenger.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.vladimir.tilikov.roomdaomessenger.data.AccessState
import ru.vladimir.tilikov.roomdaomessenger.data.UserAccessState
import ru.vladimir.tilikov.roomdaomessenger.data.contracts.SharedPrefsContract
import ru.vladimir.tilikov.roomdaomessenger.data.repositories.AuthorisationRepository
import ru.vladimir.tilikov.roomdaomessenger.data.repositories.SharedPrefsRepository
import timber.log.Timber

class LoginFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val loginKey = SharedPrefsContract.LoginForms.LOGIN
    private val passwordKey = SharedPrefsContract.LoginForms.PASSWORD

    private val repository = AuthorisationRepository()
    private val sharedPrefsRepository =
        SharedPrefsRepository(application, SharedPrefsContract.LoginForms.NAME)
    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "LoginFragmentViewModel | errorHandler")
    }

    private val _loginFormValues = MutableLiveData<Pair<String, String>>()

    val loginFormValues: LiveData<Pair<String, String>> get() = _loginFormValues

    fun checkLoginForms(login: String, password: String) {
        viewModelScope.launch(errorHandler) {
            try {
                val user = repository.getUserByLoginPassword(login, password)
                when (user) {
                    null -> {
                        UserAccessState.setAccess(0, AccessState.DENIED)
                        error("User not found")
                    }
                    else -> {
                        sharedPrefsRepository.editSharedPrefs(loginKey, login)
                        sharedPrefsRepository.editSharedPrefs(passwordKey, password)
                        UserAccessState.setAccess(user.id, AccessState.SUCCESS)
                    }
                }
            } catch (t: Throwable) {
                Timber.e(t, "Error | LoginFragmentViewModel | checkLoginForms")
            }
        }
    }

    fun getLoginFormValues() {
        viewModelScope.launch(errorHandler) {
            try {
                val login = sharedPrefsRepository.getSharedPrefsStringOf(loginKey).orEmpty()
                val password = sharedPrefsRepository.getSharedPrefsStringOf(passwordKey).orEmpty()

                _loginFormValues.value = Pair(login, password)
            } catch (t: Throwable) {
                Timber.e(t, "Error LoginFragmentViewModel: getSharedPrefsStringOf")
            }
        }
    }
}