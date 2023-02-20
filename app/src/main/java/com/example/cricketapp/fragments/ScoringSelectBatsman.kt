package com.example.cricketapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricketapp.adapters.MyMatchesListAdapter
import com.example.cricketapp.adapters.TeamsListAdapter
import com.example.cricketapp.data.Repo
import com.example.cricketapp.databinding.FragmentScoringSelectBatsmanBinding
import com.example.cricketapp.models.*

class ScoringSelectBatsman : Fragment() {

    private var _binding: FragmentScoringSelectBatsmanBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TeamsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoringSelectBatsmanBinding.inflate(inflater, container, false)

        val viewModelFactory = TeamsViewModelFactory(Repo(requireContext(), "https://www.howtodoandroid.com/"))
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[TeamsViewModel::class.java]

        viewModel.teamsListLive.observe(requireActivity()) {
            passDataToRecyclerViewAdapter(it)
        }


        return binding.root
    }

    private fun passDataToRecyclerViewAdapter(teamsList: List<TeamsModel>){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        val adapter = TeamsListAdapter(object: TeamsListAdapter.ItemClickListener{
            override fun onItemClick(modelClass: TeamsModel) {
//                Toast.makeText(requireContext(), modelClass.name, Toast.LENGTH_LONG).show()
                val action = MyTeamsFragmentDirections.actionMyTeamsFragmentToTeamDetailsFragment(modelClass.name)
                findNavController().navigate(action)
            }
        })
        adapter.submitList(teamsList)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}