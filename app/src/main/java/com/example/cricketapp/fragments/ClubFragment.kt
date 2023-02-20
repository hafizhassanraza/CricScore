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
import com.example.cricketapp.adapters.ClubListAdapter
import com.example.cricketapp.adapters.MyTournamentsListAdapter
import com.example.cricketapp.data.Repo
import com.example.cricketapp.databinding.FragmentClubBinding
import com.example.cricketapp.databinding.FragmentMyTournamentsBinding
import com.example.cricketapp.models.*

class ClubFragment : Fragment() {

    private var _binding : FragmentClubBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ClubViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentClubBinding.inflate(inflater, container, false)

        val viewModelFactory = ClubsViewModelFactory(Repo(requireContext(), "https://www.howtodoandroid.com/"))
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[ClubViewModel::class.java]
        viewModel.clubsListLive.observe(requireActivity()){
            passDataToRecyclerViewAdapter(it)
        }

        binding.buttonMyClubs.setOnClickListener {
            val action = ClubFragmentDirections.actionClubFragmentToCreateClubFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
    private fun passDataToRecyclerViewAdapter(matchesList: List<ClubModel>){
//        binding.recyclerViewClubs.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewClubs.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ClubListAdapter()
        adapter.submitList(matchesList)
        binding.recyclerViewClubs.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}