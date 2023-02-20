package com.example.cricketapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.cricketapp.R
import com.example.cricketapp.databinding.FragmentMyTeamsBinding
import com.example.cricketapp.databinding.FragmentTeamDetailsBinding

class TeamDetailsFragment : Fragment() {

    private var _teamsDetailBinding: FragmentTeamDetailsBinding? = null
    private val teamsDetailBinding get() = _teamsDetailBinding!!
    private val args: TeamDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _teamsDetailBinding = FragmentTeamDetailsBinding.inflate(layoutInflater, container, false)
        teamsDetailBinding.teamDetailsText.text = args.teamName

        return teamsDetailBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _teamsDetailBinding = null
    }
}