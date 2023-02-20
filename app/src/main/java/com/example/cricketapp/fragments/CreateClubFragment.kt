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
import com.example.cricketapp.databinding.FragmentCreateClubBinding
import com.example.cricketapp.databinding.FragmentCreateTournamentBinding
import com.example.cricketapp.models.CreateClubViewModel
import com.example.cricketapp.models.CreateClubViewModelFactory
import com.example.cricketapp.models.CreateTournamentViewModel
import com.example.cricketapp.models.CreateTournamentViewModelFactory

class CreateClubFragment : Fragment() {

    private var _binding: FragmentCreateClubBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CreateClubViewModel
    private var checkLeatherBall = false
    private var checkTapeBall = false


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentCreateClubBinding.inflate(inflater, container, false)

        val viewModelFactory = CreateClubViewModelFactory(Repo(requireContext(),"https://jsonplaceholder.typicode.com/"))
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory)[CreateClubViewModel::class.java]

        binding.createClubButtonSubmit.setOnClickListener {
            checkName()
            checkCity()
            checkYear()
            val checkBall = checkTapeBall() || checkLeatherBall()

            if (checkName() && checkCity() && checkYear() && checkBall) {
//            val tournament = CreateTournamentModel(4, 4, "Tournament Created", "Tournament Description")
//            viewModel.addTournament(tournament)
                Toast.makeText(requireContext(), "Filled", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


    private fun checkLeatherBall(): Boolean {
        checkLeatherBall = binding.createClubButtonLeather.isChecked
        binding.createClubButtonLeather.setOnCheckedChangeListener { _, isChecked ->
            checkLeatherBall = isChecked
        }
        if (!checkLeatherBall) {
            binding.clubBallTypeTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            Toast.makeText(requireContext(), "Please fill the required fields", Toast.LENGTH_SHORT)
                .show()
        }
        if (checkLeatherBall) {
            binding.clubBallTypeTitle.setTextColor(
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
        checkTapeBall = binding.createClubButtonTape.isChecked
        binding.createClubButtonTape.setOnCheckedChangeListener { _, isChecked ->
            checkTapeBall = isChecked
        }
        if (!checkTapeBall) {
            binding.clubBallTypeTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            Toast.makeText(requireContext(), "Please fill the required fields", Toast.LENGTH_SHORT)
                .show()
        }
        if (checkTapeBall) {
            binding.clubBallTypeTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }
        return checkTapeBall
    }

    private fun checkName(): Boolean {
        if (binding.createClubName.text.isEmpty()) {
            binding.createClubNameTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            Toast.makeText(requireContext(), "Please fill the required fields", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (binding.createClubName.text.isNotEmpty()) {
            binding.createClubNameTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }
        return true
    }

    private fun checkCity(): Boolean {
        if (binding.createClubCity.text.isEmpty()) {
            binding.createClubCityTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            Toast.makeText(requireContext(), "Please fill the required fields", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (binding.createClubCity.text.isNotEmpty()) {
            binding.createClubCityTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }
        return true
    }

    private fun checkYear(): Boolean {
        if (binding.createClubYear.text.isEmpty()) {
            binding.createClubYearTitle.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            Toast.makeText(requireContext(), "Please fill the required fields", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (binding.createClubYear.text.isNotEmpty()) {
            binding.createClubYearTitle.setTextColor(
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