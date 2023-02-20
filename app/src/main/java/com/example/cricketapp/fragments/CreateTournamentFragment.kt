package com.example.cricketapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.cricketapp.R
import com.example.cricketapp.data.Repo
import com.example.cricketapp.databinding.FragmentCreateTournamentBinding
import com.example.cricketapp.models.CreateTournamentModel
import com.example.cricketapp.models.CreateTournamentViewModel
import com.example.cricketapp.models.CreateTournamentViewModelFactory

class CreateTournamentFragment : Fragment() {

    private var _binding: FragmentCreateTournamentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CreateTournamentViewModel
    private var checkLeatherBall = false
    private var checkTapeBall = false

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentCreateTournamentBinding.inflate(inflater, container, false)

        val viewModelFactory = CreateTournamentViewModelFactory(Repo(requireContext(),"https://jsonplaceholder.typicode.com/"))
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory)[CreateTournamentViewModel::class.java]

        binding.createTournamentButtonSubmit.setOnClickListener {
            checkName()
            checkClub()
            checkSeason()
            val checkBall = checkTapeBall() || checkLeatherBall()

            if (checkName() && checkClub() && checkSeason() && checkBall) {
//            val tournament = CreateTournamentModel(4, 4, "Tournament Created", "Tournament Description")
//            viewModel.addTournament(tournament)
                Toast.makeText(requireContext(), "Filled", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


    private fun checkLeatherBall(): Boolean {
        checkLeatherBall = binding.createTournamentButtonLeather.isChecked
        binding.createTournamentButtonLeather.setOnCheckedChangeListener { _, isChecked ->
            checkLeatherBall = isChecked
        }
        if (!checkLeatherBall) {
            binding.tournamentBallTypeTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            Toast.makeText(requireContext(), "Please fill the required fields", Toast.LENGTH_SHORT)
                .show()
        }
        if (checkLeatherBall) {
            binding.tournamentBallTypeTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }
        Log.d("check Status", "$checkLeatherBall")
        return checkLeatherBall
    }

    private fun checkTapeBall(): Boolean {
        checkTapeBall = binding.createTournamentButtonTape.isChecked
        binding.createTournamentButtonTape.setOnCheckedChangeListener { _, isChecked ->
            checkTapeBall = isChecked
        }
        if (!checkTapeBall) {
            binding.tournamentBallTypeTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            Toast.makeText(requireContext(), "Please fill the required fields", Toast.LENGTH_SHORT)
                .show()
        }
        if (checkTapeBall) {
            binding.tournamentBallTypeTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }
        return checkTapeBall
    }

    private fun checkName(): Boolean {
        if (binding.createTournamentName.text.isEmpty()) {
            binding.createTournamentNameTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            Toast.makeText(requireContext(), "Please fill the required fields", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (binding.createTournamentName.text.isNotEmpty()) {
            binding.createTournamentNameTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }
        return true
    }

    private fun checkClub(): Boolean {
        if (binding.createTournamentClub.text.isEmpty()) {
            binding.createTournamentClubTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            Toast.makeText(requireContext(), "Please fill the required fields", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (binding.createTournamentClub.text.isNotEmpty()) {
            binding.createTournamentClubTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }
        return true
    }

    private fun checkSeason(): Boolean {
        if (binding.createTournamentSeason.text.isEmpty()) {
            binding.createTournamentSeasonTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            Toast.makeText(requireContext(), "Please fill the required fields", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (binding.createTournamentSeason.text.isNotEmpty()) {
            binding.createTournamentSeasonTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}