package com.example.cricketapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cricketapp.databinding.MatchListItemBinding
import com.example.cricketapp.models.MyMatchesModel

class MyMatchesListAdapter : ListAdapter<MyMatchesModel, MyMatchesListAdapter.MyMatchesViewHolder>(MyMatchesDiffUtils()){

    class MyMatchesViewHolder(val binding: MatchListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bin(item: MyMatchesModel){
            binding.matchItemMeta.text = item.name
            binding.matchItemLiveStatus.text = item.name
            binding.matchItemTeamOneName.text = item.name
            binding.matchItemTeamSecondName.text = item.name

            Glide.with(binding.matchItemTeamOneLogo)
                .load(item.imageUrl)
                .skipMemoryCache(true)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.matchItemTeamOneLogo)

            Glide.with(binding.matchItemTeamSecondLogo)
                .load(item.imageUrl)
                .skipMemoryCache(true)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.matchItemTeamSecondLogo)
        }
    }

    class MyMatchesDiffUtils : DiffUtil.ItemCallback<MyMatchesModel>(){
        override fun areItemsTheSame(oldItem: MyMatchesModel, newItem: MyMatchesModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: MyMatchesModel, newItem: MyMatchesModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMatchesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = MatchListItemBinding.inflate(inflater, parent, false)
        return MyMatchesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyMatchesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bin(item)
    }
}