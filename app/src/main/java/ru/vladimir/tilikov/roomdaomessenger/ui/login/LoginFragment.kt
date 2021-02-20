package ru.vladimir.tilikov.roomdaomessenger.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.vladimir.tilikov.roomdaomessenger.R
import ru.vladimir.tilikov.roomdaomessenger.data.AccessState
import ru.vladimir.tilikov.roomdaomessenger.data.UserAccessState
import ru.vladimir.tilikov.roomdaomessenger.databinding.FragmentLoginBinding
import ru.vladimir.tilikov.roomdaomessenger.utils.hideKeyboardFrom
import ru.vladimir.tilikov.roomdaomessenger.utils.toast

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val viewModel by viewModels<LoginFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLoginBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        initToolbar()
        bindViewModel()
        setListeners()
        viewModel.getLoginFormValues()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initToolbar() {
        with(binding.appBar.toolbar) {
            title = "Authorisation"
        }
    }

    private fun bindViewModel() {
        viewModel.loginFormValues.observe(viewLifecycleOwner) {
            with(binding) {
                etLogin.setText(it.first)
                etPassword.setText(it.second)
            }
        }

        UserAccessState.access.observe(viewLifecycleOwner) {
            when (it) {
                AccessState.SUCCESS -> {
                    findNavController().navigate(R.id.action_loginFragment_to_contactsFragment)
                }
                AccessState.DENIED -> toast("Need log in")
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                val login = etLogin.text.toString()
                val password = etPassword.text.toString()
                viewModel.checkLoginForms(login, password)
                requireContext().hideKeyboardFrom(loginLayout)
            }

            btnRegistration.setOnClickListener {
                findNavController().navigate(R.id.registrationDialog)
            }

            btnToContacts.setOnClickListener {
                findNavController().navigate(R.id.contactsFragment)
            }
        }
    }
}
