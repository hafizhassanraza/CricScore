package com.example.cricketapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricketapp.R
import com.example.cricketapp.adapters.MyMatchesListAdapter
import com.example.cricketapp.adapters.MyTournamentsListAdapter
import com.example.cricketapp.data.Repo
import com.example.cricketapp.databinding.FragmentMyMatchesBinding
import com.example.cricketapp.databinding.FragmentMyTournamentsBinding
import com.example.cricketapp.models.*

class MyTournamentsFragment : Fragment() {

    private var _binding : FragmentMyTournamentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyTournamentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentMyTournamentsBinding.inflate(inflater, container, false)

        val viewModelFactory = MyTournamentsViewModelFactory(Repo(requireContext(), "https://www.howtodoandroid.com/"))
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MyTournamentsViewModel::class.java]
        viewModel.tournamentsListLive.observe(requireActivity()){
            passDataToRecyclerViewAdapter(it)
        }

        binding.buttonMyTournaments.setOnClickListener {
            val action = MyTournamentsFragmentDirections.actionMyTournamentsFragmentToCreateTournamentFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
    private fun passDataToRecyclerViewAdapter(matchesList: List<MyTournamentsModel>){
        binding.recyclerViewMyTournaments.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MyTournamentsListAdapter()
        adapter.submitList(matchesList)
        binding.recyclerViewMyTournaments.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}