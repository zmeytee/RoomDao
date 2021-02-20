package ru.vladimir.tilikov.roomdaomessenger.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.vladimir.tilikov.roomdaomessenger.R
import ru.vladimir.tilikov.roomdaomessenger.data.models.User
import ru.vladimir.tilikov.roomdaomessenger.databinding.DialogRegistrationBinding
import ru.vladimir.tilikov.roomdaomessenger.utils.FormException
import ru.vladimir.tilikov.roomdaomessenger.utils.TestData
import ru.vladimir.tilikov.roomdaomessenger.utils.hideKeyboardFrom
import ru.vladimir.tilikov.roomdaomessenger.utils.toast
import timber.log.Timber
import java.time.Instant


class RegistrationDialog : Fragment() {

    private val viewModel by viewModels<RegistrationDialogViewModel>()
    private var _binding: DialogRegistrationBinding? = null
    private val binding: DialogRegistrationBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DialogRegistrationBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DialogRegistrationBinding.bind(view)

        initToolbar()
        bindViewModel()
        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initToolbar() {
        with(binding.appBar.toolbar) {
            title = "Registration"
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun bindViewModel() {
        viewModel.saveSuccess.observe(viewLifecycleOwner) {
            toast("User saved")
            findNavController().popBackStack()
        }
    }

    private fun setListeners() {
        with(binding) {
            btnSaveUser.setOnClickListener {
                try {
                    val firstName = etFirstName.text?.toString()
                    val lastName = etLastName.text?.toString()
                    val email = etEmail.text?.toString()
                    val phoneNumber = etPhoneNumber.text?.toString()
                    val avatar = etAvatar.text?.toString()
                    val login = etLogin.text?.toString()
                    val password = etPassword.text?.toString()

                    if (firstName.isNullOrBlank()) throw FormException("Login field is empty")
                    if (lastName.isNullOrBlank()) throw FormException("Login field is empty")
                    if (email.isNullOrBlank()) throw FormException("Login field is empty")
                    if (phoneNumber.isNullOrBlank()) throw FormException("Login field is empty")
                    if (login.isNullOrBlank()) throw FormException("Login field is empty")
                    if (password.isNullOrBlank()) throw FormException("Login field is empty")

                    val user = User(0, firstName, lastName, email, phoneNumber, login, password, avatar, Instant.now())

                    viewModel.saveUser(user)
                    requireContext().hideKeyboardFrom(dialogLayout)
                } catch (t: FormException) {
                    toast(t.message ?: "Login form incorrect")
                    Timber.e(t, "Login form incorrect: ${t.message}")
                } catch (t: Throwable) {
                    Timber.e(t, "Error RegistrationDialog: setListeners")
                }
            }

            ivRandom.setOnClickListener {
                etFirstName.setText(TestData.names.random())
                etLastName.setText(TestData.surnames.random())
                etEmail.setText(TestData.email)
                etPhoneNumber.setText(TestData.phoneNumber)
                etAvatar.setText(TestData.nothing)
            }

            btnCancel.setOnClickListener { findNavController().popBackStack() }
        }
    }
}