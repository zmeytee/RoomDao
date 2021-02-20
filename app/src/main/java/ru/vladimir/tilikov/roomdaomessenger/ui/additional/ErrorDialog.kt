package ru.vladimir.tilikov.roomdaomessenger.ui.additional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.vladimir.tilikov.roomdaomessenger.databinding.DialogErrorBinding

class ErrorDialog : DialogFragment() {

    private var _binding: DialogErrorBinding? = null
    private val binding: DialogErrorBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DialogErrorBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DialogErrorBinding.bind(view)
    }
}
