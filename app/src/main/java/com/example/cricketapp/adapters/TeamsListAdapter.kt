package com.example.cricketapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cricketapp.databinding.MyTeamsItemBinding
import com.example.cricketapp.models.TeamsModel

class TeamsListAdapter(private val listener: ItemClickListener) : ListAdapter<TeamsModel, TeamsListAdapter.TeamsListViewHolder>(TeamsDiffUtils()) {

    interface ItemClickListener{
        fun onItemClick(modelClass: TeamsModel)
    }

    class TeamsListViewHolder(val binding: MyTeamsItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bin(item: TeamsModel){
            binding.myTeamsTitle.text = item.name

            Glide.with(binding.myTeamsImage)
                .load(item.imageUrl)
                .skipMemoryCache(true)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.myTeamsImage)
        }
    }

    class TeamsDiffUtils: DiffUtil.ItemCallback<TeamsModel>(){
        override fun areItemsTheSame(oldItem: TeamsModel, newItem: TeamsModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TeamsModel, newItem: TeamsModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = MyTeamsItemBinding.inflate(inflater, parent, false)
        return TeamsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamsListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bin(item)

        holder.binding.root.setOnClickListener {
            listener.onItemClick(item)
        }
    }
}