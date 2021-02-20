package ru.vladimir.tilikov.roomdaomessenger.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ru.vladimir.tilikov.roomdaomessenger.R
import ru.vladimir.tilikov.roomdaomessenger.adapters.MessageAdapter
import ru.vladimir.tilikov.roomdaomessenger.data.db.Database
import ru.vladimir.tilikov.roomdaomessenger.data.models.Message
import ru.vladimir.tilikov.roomdaomessenger.databinding.FragmentMessagesBinding
import ru.vladimir.tilikov.roomdaomessenger.ui.main.BaseFragment
import ru.vladimir.tilikov.roomdaomessenger.ui.viewmodelfactories.ViewModelMessageFactory
import ru.vladimir.tilikov.roomdaomessenger.utils.hideKeyboardFrom
import ru.vladimir.tilikov.roomdaomessenger.utils.toast
import java.time.Instant

class MessagesFragment : BaseFragment() {

    private var _binding: FragmentMessagesBinding? = null
    private val binding: FragmentMessagesBinding get() = _binding!!
    private lateinit var viewModel: MessagesFragmentViewModel
    private val args by navArgs<MessagesFragmentArgs>()
    private var messageAdapter: MessageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMessagesBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMessagesBinding.bind(view)
        val messageDao = Database.instance.messageDao()
        viewModel = ViewModelProvider(this, ViewModelMessageFactory(args.contact.id, messageDao))
            .get(MessagesFragmentViewModel::class.java)

        initToolbar()
        initList()
        bindViewModel()
        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        messageAdapter = null
    }

    private fun initToolbar() {
        with(binding.appBar.toolbar) {
            title = args.contact.name
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun initList() {
        messageAdapter = MessageAdapter(
            onItemClick = { viewModel.changeMessageStatus(it.messageWithFiles.message.id) },
            onItemLongClick = { viewModel.deleteMessage(it.messageWithFiles.message.id) }
        )
        with(binding.rvMessages) {
            adapter = messageAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        with(viewModel) {
            messagesList.observe(viewLifecycleOwner) { messageAdapter?.submitList(it) }
            deletingComplete.observe(viewLifecycleOwner) { toast("Deleting complete") }
        }
    }

    private fun setListeners() {
        with(binding) {
            ivAddMessage.setOnClickListener {
                val text = etMessage.text.toString()
                val message = Message(
                    id = 0,
                    contactId = args.contact.id,
                    message = text,
                    createdAt = Instant.now()
                )
                viewModel.addMessage(message)
                etMessage.text?.clear()
                requireContext().hideKeyboardFrom(etMessage)
            }

            ivAddFile.setOnClickListener {
                requireContext().hideKeyboardFrom(etMessage)
                val action = MessagesFragmentDirections.actionMessagesFragmentToImageListFragment(args.contact)
                findNavController().navigate(action)
            }
        }
    }
}
