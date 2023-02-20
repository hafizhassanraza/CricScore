package com.example.cricketapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cricketapp.databinding.NewsCardItemBinding
import com.example.cricketapp.models.NewsModel

class NewsRecyclerViewAdapter(private val list: List<NewsModel>, private val listener: ItemClickListener) : RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>(){

    interface ItemClickListener{
        fun onItemClick(url: String)
    }

    class NewsViewHolder(val binding: NewsCardItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = NewsCardItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.newsItemHeading.text = list[position].name
        holder.binding.newsItemDesc.text = list[position].desc
        holder.binding.newsItemSource.text = list[position].category

        Glide.with(holder.binding.newsItemImage)
            .load(list[position].imageUrl)
            .skipMemoryCache(true)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(holder.binding.newsItemImage)

        holder.binding.root.setOnClickListener {
//            listener.onItemClick(list[position].imageUrl)
            listener.onItemClick("https://www.espn.in/nba/story/_/id/35512961/the-hoop-collective-why-mike-brown-growth-coach-kings-beaming")
        }
    }
}