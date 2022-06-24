package com.java90apps.spotifyrebus.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.java90apps.spotifyrebus.databinding.ItemPodcastLayoutBinding
import com.java90apps.spotifyrebus.domain.models.ChannelModel
import java.text.SimpleDateFormat

class ChannelsAdapter(
    private val onItemClicked: (Int) -> Unit
) : ListAdapter<ChannelModel, ChannelViewHolder>(ChannelItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        return ChannelViewHolder(
            ItemPodcastLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val channel = getItem(position)
        holder.bind(channel)
        holder.itemView.setOnClickListener {
            onItemClicked(channel.id)
        }
    }
}

class ChannelViewHolder(val binding: ItemPodcastLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ChannelModel) {
        with(binding) {
            Glide.with(root)
                .load(item.urls.logo_image.original)
                .into(ivChannel)

            tvChannelTitle.text = item.title
            tvChannelDescription.text = item.description
            tvChannelUpdated.text = item.updated_at.substringBefore("T")
        }
    }
}


class ChannelItemCallback : DiffUtil.ItemCallback<ChannelModel>() {
    override fun areItemsTheSame(oldItem: ChannelModel, newItem: ChannelModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChannelModel, newItem: ChannelModel): Boolean {
        return true
    }
}