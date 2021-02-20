package ru.vladimir.tilikov.roomdaomessenger.ui.contacts.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.vladimir.tilikov.roomdaomessenger.R
import ru.vladimir.tilikov.roomdaomessenger.databinding.FragmentContactAddBinding
import ru.vladimir.tilikov.roomdaomessenger.ui.main.BaseFragment
import ru.vladimir.tilikov.roomdaomessenger.utils.TestData

class ContactAddFragment: BaseFragment() {

    private var _binding: FragmentContactAddBinding? = null
    private val binding: FragmentContactAddBinding get() = _binding!!
    private val viewModel by viewModels<ContactAddFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentContactAddBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContactAddBinding.bind(view)

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
            title = "Add contact"
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun bindViewModel() {
        with(viewModel) {
            addComplete.observe(viewLifecycleOwner) {
//                findNavController().popBackStack()
            }
        }
    }

    private fun setListeners() {
        binding.btnAddContact.setOnClickListener {
            viewModel.addContact(TestData.getRandomName())
        }
    }
}