package com.example.cricketapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricketapp.adapters.MyMatchesListAdapter
import com.example.cricketapp.data.Repo
import com.example.cricketapp.databinding.FragmentMyMatchesBinding
import com.example.cricketapp.models.*

class MyMatchesFragment : Fragment() {

    private var _binding : FragmentMyMatchesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyMatchesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentMyMatchesBinding.inflate(inflater, container, false)

        val viewModelFactory = MyMatchesViewModelFactory(Repo(requireContext(), "https://www.howtodoandroid.com/"))
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MyMatchesViewModel::class.java]
        viewModel.myMatchesListLive.observe(requireActivity()){
            passDataToRecyclerViewAdapter(it)
        }

        binding.myMatchesBtn.setOnClickListener {
            val action = MyMatchesFragmentDirections.actionMyMatchesFragmentToCreateMatchFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun passDataToRecyclerViewAdapter(matchesList: List<MyMatchesModel>){
        binding.recyclerViewMyMatches.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MyMatchesListAdapter()
        adapter.submitList(matchesList)
        binding.recyclerViewMyMatches.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}