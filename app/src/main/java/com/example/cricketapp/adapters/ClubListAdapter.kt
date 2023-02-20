package com.example.cricketapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cricketapp.databinding.ClubListItemBinding
import com.example.cricketapp.models.ClubModel

class ClubListAdapter: ListAdapter<ClubModel, ClubListAdapter.ClubViewHolder>(ClubDiffUtils()){

    class ClubViewHolder(val binding: ClubListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bin(item: ClubModel){
            binding.clubItemName.text = item.name
            binding.clubItemCity.text = item.name
            binding.clubItemDesc.text = item.name

            Glide.with(binding.clubItemLogo)
                .load(item.imageUrl)
                .skipMemoryCache(true)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.clubItemLogo)

            Glide.with(binding.clubItemLeatherBall)
                .load(item.imageUrl)
                .skipMemoryCache(true)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.clubItemLeatherBall)

            Glide.with(binding.clubItemTapeBall)
                .load(item.imageUrl)
                .skipMemoryCache(true)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.clubItemTapeBall)
        }
    }

    class ClubDiffUtils : DiffUtil.ItemCallback<ClubModel>(){
        override fun areItemsTheSame(oldItem: ClubModel, newItem: ClubModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ClubModel, newItem: ClubModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ClubListItemBinding.inflate(inflater, parent, false)
        return ClubViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        val item = getItem(position)
        holder.bin(item)
    }
}