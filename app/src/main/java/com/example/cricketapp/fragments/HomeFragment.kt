package com.example.cricketapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricketapp.adapters.ClubListAdapter
import com.example.cricketapp.adapters.MyMatchesListAdapter
import com.example.cricketapp.adapters.MyTournamentsListAdapter
import com.example.cricketapp.data.Repo
import com.example.cricketapp.databinding.FragmentHomeBinding
import com.example.cricketapp.models.*

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelMatches: MyMatchesViewModel
    private lateinit var viewModelTournaments: MyTournamentsViewModel
    private lateinit var viewModelClub: ClubViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val viewModelFactoryMatches = MyMatchesViewModelFactory(Repo(requireContext(), "https://www.howtodoandroid.com/"))
        viewModelMatches = ViewModelProvider(requireActivity(), viewModelFactoryMatches)[MyMatchesViewModel::class.java]
        viewModelMatches.myMatchesListLive.observe(requireActivity()){
            passDataToMyMatchesAdapter(it)
        }

        val viewModelFactoryTournaments = MyTournamentsViewModelFactory(Repo(requireContext(), "https://www.howtodoandroid.com/"))
        viewModelTournaments = ViewModelProvider(requireActivity(), viewModelFactoryTournaments)[MyTournamentsViewModel::class.java]
        viewModelTournaments.tournamentsListLive.observe(requireActivity()){
            passDataToMyTournamentsAdapter(it)
        }

        val viewModelFactoryClubs = ClubsViewModelFactory(Repo(requireContext(), "https://www.howtodoandroid.com/"))
        viewModelClub = ViewModelProvider(requireActivity(), viewModelFactoryClubs)[ClubViewModel::class.java]
        viewModelClub.clubsListLive.observe(requireActivity()){
            passDataToClubsAdapter(it)
        }


        return binding.root
    }

    private fun passDataToMyMatchesAdapter(matchesList: List<MyMatchesModel>){
        binding.homeRecyclerMatches.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = MyMatchesListAdapter()
        adapter.submitList(matchesList)
        binding.homeRecyclerMatches.adapter = adapter
    }

    private fun passDataToMyTournamentsAdapter(matchesList: List<MyTournamentsModel>){
        binding.homeRecyclerTournaments.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = MyTournamentsListAdapter()
        adapter.submitList(matchesList)
        binding.homeRecyclerTournaments.adapter = adapter
    }

    private fun passDataToClubsAdapter(matchesList: List<ClubModel>){
        binding.homeRecyclerClubs.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = ClubListAdapter()
        adapter.submitList(matchesList)
        binding.homeRecyclerClubs.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}