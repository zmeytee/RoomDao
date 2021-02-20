package ru.vladimir.tilikov.roomdaomessenger.adapters

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.clear
import coil.load
import ru.vladimir.tilikov.roomdaomessenger.R
import ru.vladimir.tilikov.roomdaomessenger.data.models.relations.MessageWithStatus
import ru.vladimir.tilikov.roomdaomessenger.databinding.ItemMessageBinding
import ru.vladimir.tilikov.roomdaomessenger.utils.inflate


class MessageAdapter(
    private val onItemClick: (message: MessageWithStatus) -> Unit,
    private val onItemLongClick: (message: MessageWithStatus) -> Unit
) : ListAdapter<MessageWithStatus, MessageAdapter.Holder>(MessageDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_message), onItemClick, onItemLongClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class MessageDiffUtilCallback : DiffUtil.ItemCallback<MessageWithStatus>() {
        override fun areItemsTheSame(
            oldItem: MessageWithStatus,
            newItem: MessageWithStatus
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MessageWithStatus,
            newItem: MessageWithStatus
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        view: View,
        onItemClick: (message: MessageWithStatus) -> Unit,
        onItemLongClick: (message: MessageWithStatus) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val binding = ItemMessageBinding.bind(view)
        private var message: MessageWithStatus? = null

        init {
            with(binding.root) {
                setOnClickListener {
                    message?.let(onItemClick)
                }
                setOnLongClickListener {
                    message?.let(onItemLongClick)
                    true
                }
            }
        }

        fun bind(item: MessageWithStatus) {
            message = item
            with(binding) {
                tvMessage.text = item.messageWithFiles.message.message
                ivMessageStatus.isVisible = item.messageStatus.status
                tvDate.text = item.messageWithFiles.message.createdAt.toString()
                val images = item.messageWithFiles.files
                if (images.isNotEmpty()) {
                    ivAttachment.load(images.first().fileUri)
                } else {
                    ivAttachment.clear()
                }
            }
        }
    }
}