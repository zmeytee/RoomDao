package ru.vladimir.tilikov.roomdaomessenger.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.vladimir.tilikov.roomdaomessenger.R
import ru.vladimir.tilikov.roomdaomessenger.data.entities.Image
import ru.vladimir.tilikov.roomdaomessenger.databinding.ItemImageBinding
import ru.vladimir.tilikov.roomdaomessenger.utils.inflate

class ImageAdapter (
        private val onItemClick: (image: Image) -> Unit
    ) : ListAdapter<Image, ImageAdapter.Holder>(ImageDiffUtilCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(parent.inflate(R.layout.item_image), onItemClick)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(getItem(position))
        }

        class ImageDiffUtilCallback : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }
        }

        class Holder(
            view: View,
            onItemClick: (image: Image) -> Unit
        ) : RecyclerView.ViewHolder(view) {

            private val binding = ItemImageBinding.bind(view)
            private var currentImage: Image? = null

            init {
                with(binding) {
                    root.setOnClickListener { currentImage?.let(onItemClick) }
                }
            }

            fun bind(item: Image) {
                currentImage = item

                binding.ivImage.load(item.uri) {
                    placeholder(R.drawable.ic_close)
                    crossfade(1000)
                }
            }
        }
    }