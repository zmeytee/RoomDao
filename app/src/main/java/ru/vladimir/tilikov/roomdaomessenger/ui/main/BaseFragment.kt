package ru.vladimir.tilikov.roomdaomessenger.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.vladimir.tilikov.roomdaomessenger.R
import ru.vladimir.tilikov.roomdaomessenger.data.AccessState
import ru.vladimir.tilikov.roomdaomessenger.data.UserAccessState

open class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        UserAccessState.access.observe(viewLifecycleOwner) {
            when (it) {
                AccessState.DENIED -> findNavController().navigate(R.id.loginFragment)
                AccessState.SUCCESS -> {}
            }
        }
    }
}