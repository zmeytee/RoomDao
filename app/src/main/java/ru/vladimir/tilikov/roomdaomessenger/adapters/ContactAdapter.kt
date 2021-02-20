package ru.vladimir.tilikov.roomdaomessenger.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.vladimir.tilikov.roomdaomessenger.R
import ru.vladimir.tilikov.roomdaomessenger.data.models.Contact
import ru.vladimir.tilikov.roomdaomessenger.databinding.ItemContactBinding
import ru.vladimir.tilikov.roomdaomessenger.utils.inflate

class ContactAdapter(
    private val onItemClick: (contact: Contact) -> Unit,
    private val onClickDelete: (contact: Contact) -> Unit
) : ListAdapter<Contact, ContactAdapter.Holder>(ContactDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_contact), onItemClick, onClickDelete)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class ContactDiffUtilCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        view: View,
        onItemClick: (contact: Contact) -> Unit,
        onClickDelete: (contact: Contact) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val binding = ItemContactBinding.bind(view)
        private var currentContact: Contact? = null

        init {
            with(binding) {
                root.setOnClickListener { currentContact?.let(onItemClick) }
                ivDelete.setOnClickListener { currentContact?.let(onClickDelete) }
            }
        }

        fun bind(item: Contact) {
            currentContact = item
            binding.tvMessage.text = item.name
        }
    }
}