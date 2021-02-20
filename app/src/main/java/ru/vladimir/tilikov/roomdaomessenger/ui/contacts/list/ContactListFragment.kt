package ru.vladimir.tilikov.roomdaomessenger.ui.contacts.list

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.vladimir.tilikov.roomdaomessenger.adapters.ContactAdapter
import ru.vladimir.tilikov.roomdaomessenger.data.AccessState
import ru.vladimir.tilikov.roomdaomessenger.data.UserAccessState
import ru.vladimir.tilikov.roomdaomessenger.data.db.Database
import ru.vladimir.tilikov.roomdaomessenger.databinding.FragmentContactListBinding
import ru.vladimir.tilikov.roomdaomessenger.ui.main.BaseFragment
import ru.vladimir.tilikov.roomdaomessenger.ui.viewmodelfactories.ViewModelContactFactory
import ru.vladimir.tilikov.roomdaomessenger.utils.toast

class ContactListFragment : BaseFragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding: FragmentContactListBinding get() = _binding!!
    private lateinit var viewModel: ContactListFragmentViewModel
    private var contactsAdapter: ContactAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentContactListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContactListBinding.bind(view)
        val contactDao = Database.instance.contactDao()
        viewModel = ViewModelProvider(this, ViewModelContactFactory(contactDao))
            .get(ContactListFragmentViewModel::class.java)

        initToolbar()
        initList()
        bindViewModel()
        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        contactsAdapter = null
    }

    private fun initToolbar() {
        binding.appBar.toolbar.title = "Contacts"
    }

    private fun initList() {
        contactsAdapter = ContactAdapter(
            onItemClick = {
                val action = ContactListFragmentDirections
                    .actionContactsFragmentToMessagesFragment(it)
                findNavController().navigate(action)
            },
            onClickDelete = { viewModel.deleteContact(it.id) }
        )
        with(binding.rvContacts) {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        with(viewModel) {
            contactsList.observe(viewLifecycleOwner) { contactsAdapter?.submitList(it) }
            deletingComplete.observe(viewLifecycleOwner) { toast("Deleting complete") }
        }
    }

    private fun setListeners() {
        with(binding.fabMenu) {
            fabMenuSwitcher.setOnClickListener { switchFabMenuVisibility() }

            fabAddContact.setOnClickListener {
                val action =
                    ContactListFragmentDirections.actionContactsFragmentToContactAddFragment()
                findNavController().navigate(action)
            }

            fabProfile.setOnClickListener {
                val action = ContactListFragmentDirections.actionContactsFragmentToProfileFragment()
                findNavController().navigate(action)
            }

            fabLogout.setOnClickListener {
                with(UserAccessState) { setAccess(activeUserId, AccessState.DENIED) }
            }
        }
    }

    private fun switchFabMenuVisibility() {

        val set = TransitionSet()
//            .addTransition(Slide(Gravity.TOP))
            .addTransition(Fade())
            .setOrdering(TransitionSet.ORDERING_TOGETHER)
            .setDuration(300)
            .setInterpolator(AccelerateDecelerateInterpolator())

        with(binding.fabMenu) {
            val fabChildren = listOf(
                fabAddContact,
                fabProfile,
                fabLogout
            )

            TransitionManager.beginDelayedTransition(binding.fabMenu.root, set)
            fabChildren.forEach { it.isVisible = it.isVisible.not() }
        }
    }
}
