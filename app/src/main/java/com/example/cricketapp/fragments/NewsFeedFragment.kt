package com.example.cricketapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricketapp.adapters.NewsRecyclerViewAdapter
import com.example.cricketapp.data.Repo
import com.example.cricketapp.databinding.FragmentNewsFeedBinding
import com.example.cricketapp.models.NewsModel
import com.example.cricketapp.models.NewsViewModel
import com.example.cricketapp.models.NewsViewModelFactory


class NewsFeedFragment : Fragment() {

    private var _newsBinding : FragmentNewsFeedBinding? = null
    private val newsBinding get() = _newsBinding!!
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _newsBinding = FragmentNewsFeedBinding.inflate(inflater, container, false)

        val viewModelFactory = NewsViewModelFactory(Repo(requireContext(), "https://www.howtodoandroid.com/"))
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[NewsViewModel::class.java]
        viewModel.newsListLive.observe(requireActivity()){
            passDataToRecyclerViewAdapter(it)
        }

        return newsBinding.root
    }

    private fun passDataToRecyclerViewAdapter(newsList: List<NewsModel>){
        newsBinding.recyclerViewNews.layoutManager = LinearLayoutManager(requireContext())
        val adapter = NewsRecyclerViewAdapter(newsList, object : NewsRecyclerViewAdapter.ItemClickListener{
            override fun onItemClick(url: String) {
                val action = NewsFeedFragmentDirections.actionNewsFeedFragmentToReadNewsFragment(url)
                findNavController().navigate(action)
            }
        })
        newsBinding.recyclerViewNews.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _newsBinding = null
    }
}