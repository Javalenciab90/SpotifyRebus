package com.java90apps.spotifyrebus.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.java90apps.spotifyrebus.databinding.ItemClipLayoutBinding
import com.java90apps.spotifyrebus.domain.models.AudioClipModel

class PlayListAdapter(
    private val onItemClicked: (AudioClipModel) -> Unit
) : ListAdapter<AudioClipModel, ClipViewHolder>(ClipItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipViewHolder {
        return ClipViewHolder(
            ItemClipLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ClipViewHolder, position: Int) {
        val clip = getItem(position)
        holder.bind(clip)
        holder.itemView.setOnClickListener {
            onItemClicked(clip)
        }
    }
}

class ClipViewHolder(val binding: ItemClipLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AudioClipModel) {
        with(binding) {
            Glide.with(root)
                .load(item.urls.image)
                .into(ivClipTitle)

            tvClipTitle.text = item.title
            tvClipDescription.text = item.description
            tvClipUpdated.text = item.updated_at.substringBefore("T")

        }
    }
}

class ClipItemCallback : DiffUtil.ItemCallback<AudioClipModel>() {
    override fun areItemsTheSame(oldItem: AudioClipModel, newItem: AudioClipModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AudioClipModel, newItem: AudioClipModel): Boolean {
        return true
    }
}