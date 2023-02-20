package com.example.cricketapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.cricketapp.databinding.TournamentsListItemBinding
import com.example.cricketapp.models.MyTournamentsModel

class MyTournamentsListAdapter : ListAdapter<MyTournamentsModel, MyTournamentsListAdapter.MyTournamentsViewHolder>(MyTournamentsDiffUtils()){

    class MyTournamentsViewHolder(val binding: TournamentsListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bin(item: MyTournamentsModel){
            binding.tournamentItemName.text = item.name
            binding.tournamentItemCity.text = item.name
            binding.tournamentItemClub.text = item.name

            Glide.with(binding.tournamentItemBanner)
                .load(item.imageUrl)
                .skipMemoryCache(true)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.tournamentItemBanner)
        }
    }

    class MyTournamentsDiffUtils : DiffUtil.ItemCallback<MyTournamentsModel>(){
        override fun areItemsTheSame(oldItem: MyTournamentsModel,newItem: MyTournamentsModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: MyTournamentsModel,newItem: MyTournamentsModel): Boolean {
            return oldItem == newItem
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTournamentsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TournamentsListItemBinding.inflate(inflater, parent, false)
        return MyTournamentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyTournamentsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bin(item)
    }
}