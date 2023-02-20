package com.example.cricketapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cricketapp.databinding.FragmentCreateMatchBinding

class CreateMatchFragment : Fragment() {

    private var _binding : FragmentCreateMatchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateMatchBinding.inflate(inflater, container, false)
        binding.createMatchImgA.setOnClickListener {

        }

        binding.createMatchImgA.setOnClickListener {

        }


        binding.createMatchBtnStart.setOnClickListener {
            val action = CreateMatchFragmentDirections.actionCreateMatchFragmentToScoringFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}