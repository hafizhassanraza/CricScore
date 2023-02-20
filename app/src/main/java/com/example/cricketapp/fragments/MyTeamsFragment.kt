package com.example.cricketapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cricketapp.ApplicationClass
import com.example.cricketapp.R
import com.example.cricketapp.adapters.TeamsListAdapter
import com.example.cricketapp.data.Repo
import com.example.cricketapp.databinding.FragmentMyTeamsBinding
import com.example.cricketapp.models.*

class MyTeamsFragment : Fragment() {

    private var _teamsBinding: FragmentMyTeamsBinding? = null
    private val teamsBinding get() = _teamsBinding!!
    private lateinit var teamViewModel: TeamsViewModel
    lateinit var teamsList: List<TeamsModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _teamsBinding = FragmentMyTeamsBinding.inflate(inflater, container, false)


        val viewModelFactory = TeamsViewModelFactory(Repo(requireContext(), "https://www.howtodoandroid.com/"))
        teamViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[TeamsViewModel::class.java]

//        activity?.addMenuProvider(object: MenuProvider{
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                menuInflater.inflate(R.menu.home_menu, menu)
//            }
//
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                return menuItem.onNavDestinationSelected(findNavController())
//            }
//        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        teamViewModel.teamsListLive.observe(viewLifecycleOwner){
            teamsList = it
            passDataToRecyclerViewAdapter(teamsList)
            teamViewModel.addTeam(teamsList)
        }

        teamViewModel.fetchTeams().observe(viewLifecycleOwner){
            Log.d("Teams Database List: ", "$it \n")
        }

//        teamsBinding.buttonMyTeams.setOnClickListener {
//            teamViewModel.addTeamToRetrofit(PostModel(3, 4,"Testing Retrofit Post", "Testing Retrofit Body"))
//        }

        return teamsBinding.root
    }

    private fun passDataToRecyclerViewAdapter(teamsList: List<TeamsModel>){
        teamsBinding.recyclerViewMyTeams.layoutManager = GridLayoutManager(requireContext(), 3)
        teamsBinding.recyclerViewMyTeams.setHasFixedSize(true)
        val adapter = TeamsListAdapter(object: TeamsListAdapter.ItemClickListener{
            override fun onItemClick(modelClass: TeamsModel) {
//                Toast.makeText(requireContext(), modelClass.name, Toast.LENGTH_LONG).show()
                val action = MyTeamsFragmentDirections.actionMyTeamsFragmentToTeamDetailsFragment(modelClass.name)
                findNavController().navigate(action)
            }
        })
        adapter.submitList(teamsList)
        teamsBinding.recyclerViewMyTeams.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _teamsBinding = null
    }
}