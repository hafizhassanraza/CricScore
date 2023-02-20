package com.example.cricketapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cricketapp.data.Repo
import com.example.cricketapp.databinding.FragmentScoringBinding
import com.example.cricketapp.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class ScoringFragment : Fragment() {

    private var _binding: FragmentScoringBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ScoringViewModel

    private var currentBatsman = true
    private var currentBatsmanLive = MutableLiveData(true)
    private var subOver = 0.0F
    private var overs = 0.0F

    private var matchSubOver = 0.0F
    private var matchOvers = 0.0F
    private var totalMatchOvers = 50

    private var matchInfo =
        PlayingMatchModel(0, 0, 0, 0.0F, totalMatchOvers, 0.0F, 0, 0)

    private var batsmanOne =
        PlayingBatsmanModel("null", 0, 0, 0, 0, 0.0F)

    private var batsmanTwo =
        PlayingBatsmanModel("null", 0, 0, 0, 0, 0.0F)

    private var bowler =
        PlayingBowlerModel(0.0F, 0, 0, 0, 0.0F)


    private val bowlersList = mutableListOf<PlayingBowlerModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoringBinding.inflate(inflater, container, false)
        val factory =
            ScoringViewModelFactory(Repo(requireContext(), "https://www.howtodoandroid.com/"))
        viewModel = ViewModelProvider(requireActivity(), factory)[ScoringViewModel::class.java]

        batsmanOne.playerName = "Ahmed"
        batsmanTwo.playerName = "Ali"


        viewModel.match.observe(viewLifecycleOwner) {
            matchInfo.score = it.score
            matchInfo.overs = it.overs
            matchInfo.cRR = it.cRR
            binding.scoringTVScore.text = "${matchInfo.score} - ${matchInfo.wickets}"
            binding.scoringTVOvers.text = "Overs - ${matchInfo.overs}/$totalMatchOvers"
            binding.scoringTVCRR.text = "CRR - ${matchInfo.cRR}"
            binding.scoringTVPartnership.text = "Partnership - ${matchPartnership()}"
        }

        viewModel.batsmanOne.observe(viewLifecycleOwner) {
            batsmanOne.score = it.score
            batsmanOne.ballsPlayed = it.ballsPlayed
            batsmanOne.fours = it.fours
            batsmanOne.sixes = it.sixes
            batsmanOne.strikeRate = it.strikeRate
            binding.scoringTVBatsmanOneRuns.text = it.score.toString()
            binding.scoringTVBatsmanOneBalls.text = it.ballsPlayed.toString()
            binding.scoringTVBatsmanOne4s.text = it.fours.toString()
            binding.scoringTVBatsmanOne6s.text = it.sixes.toString()
            binding.scoringTVBatsmanOneSR.text = it.strikeRate.toString()
        }

        viewModel.batsmanTwo.observe(viewLifecycleOwner) {
            batsmanTwo.score = it.score
            batsmanTwo.ballsPlayed = it.ballsPlayed
            batsmanTwo.fours = it.fours
            batsmanTwo.sixes = it.sixes
            batsmanTwo.strikeRate = it.strikeRate
            binding.scoringTVBatsmanTwoRuns.text = it.score.toString()
            binding.scoringTVBatsmanTwoBalls.text = it.ballsPlayed.toString()
            binding.scoringTVBatsmanTwo4s.text = it.fours.toString()
            binding.scoringTVBatsmanTwo6s.text = it.sixes.toString()
            binding.scoringTVBatsmanTwoSR.text = it.strikeRate.toString()
        }

        viewModel.bowler.observe(viewLifecycleOwner) {
            bowler.overs = it.overs
            bowler.runs = it.runs
            bowler.economy = it.economy
            binding.scoringTVBowlerOneOvers.text = it.overs.toString()
            binding.scoringTVBowlerOneRuns.text = it.runs.toString()
            binding.scoringTVBowlerOneEco.text = it.economy.toString()
        }

        currentBatsmanLive.observe(viewLifecycleOwner){
            if (it){
                binding.scoringTVBatsmanOne.text = "${batsmanOne.playerName}*"
                binding.scoringTVBatsmanTwo.text = "${batsmanTwo.playerName}"
            }
            if (!it){
                binding.scoringTVBatsmanOne.text = "${batsmanOne.playerName}"
                binding.scoringTVBatsmanTwo.text = "${batsmanTwo.playerName}*"
            }
        }

        binding.scoreHome1.setOnClickListener {
            viewModel.updateMatch(score = matchInfo.score + 1, overs = matchOvers(), cRR = matchCRR())
            viewModel.updateBowler(overs = over(), runs = bowler.runs + 1, economy = bowlerEco(bowler.runs, bowler.overs))
            if (currentBatsmanLive.value!!) {
                viewModel.updateBatsmanOne(
                    score = batsmanOne.score + 1,
                    balls = batsmanOne.ballsPlayed + 1,
                    four = batsmanOne.fours,
                    six = batsmanOne.sixes,
                    strikeRate = strikeRate(batsmanOne.score, batsmanOne.ballsPlayed)
                )
            }

            if (!currentBatsmanLive.value!!) {
                viewModel.updateBatsmanTwo(
                    score = batsmanTwo.score + 1,
                    balls = batsmanTwo.ballsPlayed + 1,
                    four = batsmanTwo.fours,
                    six = batsmanTwo.sixes,
                    strikeRate = strikeRate(batsmanTwo.score, batsmanTwo.ballsPlayed)
                )
            }

            currentBatsmanLive.value = !currentBatsmanLive.value!!
        }

        binding.scoreHome2.setOnClickListener {
            viewModel.updateMatch(score = matchInfo.score + 2, overs = matchOvers(), cRR = matchCRR())
            viewModel.updateBowler(overs = over(), runs = bowler.runs + 2, economy = bowlerEco(bowler.runs, bowler.overs))
            if (currentBatsmanLive.value!!) {
                viewModel.updateBatsmanOne(
                    score = batsmanOne.score + 2,
                    balls = batsmanOne.ballsPlayed + 1,
                    four = batsmanOne.fours,
                    six = batsmanOne.sixes,
                    strikeRate = strikeRate(batsmanOne.score, batsmanOne.ballsPlayed)

                )
            }

            if (!currentBatsmanLive.value!!) {
                viewModel.updateBatsmanTwo(
                    score = batsmanTwo.score + 2,
                    balls = batsmanTwo.ballsPlayed + 1,
                    four = batsmanTwo.fours,
                    six = batsmanTwo.sixes,
                    strikeRate = strikeRate(batsmanTwo.score, batsmanTwo.ballsPlayed)
                )
            }
        }

        binding.scoreHome3.setOnClickListener {
            viewModel.updateMatch(score = matchInfo.score + 3, overs = matchOvers(), cRR = matchCRR())
            viewModel.updateBowler(overs = over(), runs = bowler.runs + 3, economy = bowlerEco(bowler.runs, bowler.overs))
            if (currentBatsmanLive.value!!) {
                viewModel.updateBatsmanOne(
                    score = batsmanOne.score + 3,
                    balls = batsmanOne.ballsPlayed +1,
                    four = batsmanOne.fours,
                    six = batsmanOne.sixes,
                    strikeRate = strikeRate(batsmanOne.score, batsmanOne.ballsPlayed)
                )
            }

            if (!currentBatsmanLive.value!!) {
                viewModel.updateBatsmanTwo(
                    score = batsmanTwo.score + 3,
                    balls = batsmanTwo.ballsPlayed +1,
                    four = batsmanTwo.fours,
                    six = batsmanTwo.sixes,
                    strikeRate = strikeRate(batsmanTwo.score, batsmanTwo.ballsPlayed)
                )
            }

            currentBatsmanLive.value = !currentBatsmanLive.value!!
        }

        binding.scoreHome4.setOnClickListener {
            viewModel.updateMatch(score = matchInfo.score + 4, overs = matchOvers(), cRR = matchCRR())
            viewModel.updateBowler(overs = over(), runs = bowler.runs + 4, economy = bowlerEco(bowler.runs, bowler.overs))
            if (currentBatsmanLive.value!!) {
                viewModel.updateBatsmanOne(
                    score = batsmanOne.score + 4,
                    balls = batsmanOne.ballsPlayed + 1,
                    four = batsmanOne.fours + 1,
                    six = batsmanOne.sixes,
                    strikeRate = strikeRate(batsmanOne.score, batsmanOne.ballsPlayed)
                )
            }

            if (!currentBatsmanLive.value!!) {
                viewModel.updateBatsmanTwo(
                    score = batsmanTwo.score + 4,
                    balls = batsmanTwo.ballsPlayed + 1,
                    four = batsmanTwo.fours + 1,
                    six = batsmanTwo.sixes,
                    strikeRate = strikeRate(batsmanTwo.score, batsmanTwo.ballsPlayed)
                )
            }
        }

        binding.scoreHome6.setOnClickListener {
            viewModel.updateMatch(score = matchInfo.score + 6, overs = matchOvers(), cRR = matchCRR())
            viewModel.updateBowler(overs = over(), runs = bowler.runs + 6, economy = bowlerEco(bowler.runs, bowler.overs))
            if (currentBatsmanLive.value!!) {
                viewModel.updateBatsmanOne(
                    score = batsmanOne.score + 6,
                    balls = batsmanOne.ballsPlayed + 1,
                    four = batsmanOne.fours,
                    six = batsmanOne.sixes + 1,
                    strikeRate = strikeRate(batsmanOne.score, batsmanOne.ballsPlayed)
                )
            }

            if (!currentBatsmanLive.value!!) {
                viewModel.updateBatsmanTwo(
                    score = batsmanTwo.score + 6,
                    balls = batsmanTwo.ballsPlayed + 1,
                    four = batsmanTwo.fours,
                    six = batsmanTwo.sixes + 1,
                    strikeRate = strikeRate(batsmanTwo.score, batsmanTwo.ballsPlayed)
                )
            }
        }

        binding.scoringTVBatsmanOne.setOnClickListener {
            currentBatsmanLive.value = true
        }
        binding.scoringTVBatsmanTwo.setOnClickListener {
            currentBatsmanLive.value = false
        }
        binding.scoringTVBowler.setOnClickListener {
            //open batsman selection screen
        }

        binding.scoringTVBatsmanOne.setOnClickListener {
            val action = ScoringFragmentDirections.actionScoringFragmentToScoringSelectBatsman()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun over(): Float {
        subOver += 0.1F
        subOver = DecimalFormat("###.#").format(subOver).toFloat()
        if (subOver == 0.6F) {
            subOver = 0.0F
            overs += 1.0F
        }
        return overs + subOver
    }

    private fun matchOvers(): Float {
        matchSubOver += 0.1F
        matchSubOver = DecimalFormat("###.#").format(matchSubOver).toFloat()
        if (matchSubOver == 0.6F) {
            matchSubOver = 0.0F
            matchOvers += 1.0F

            bowlersList.add(PlayingBowlerModel(over(), bowler.maiden, bowler.runs, bowler.wickets, bowler.economy))
        }
        return matchOvers + matchSubOver
    }

    private fun strikeRate(runsScored: Int, ballsFaced: Int) : Float{
        if (ballsFaced > 0){
            return (runsScored.toFloat()*100)/ballsFaced.toFloat()
        }
        return 0.0F
    }

    private fun bowlerEco(runsConceded: Int, oversBowled: Float) : Float{
        if(oversBowled > 0){
            return runsConceded/oversBowled
        }
        return 0.0F
    }

    private fun matchCRR() : Float{
        if (matchInfo.overs > 0){
            return matchInfo.score/matchInfo.overs
        }
        return 0.0F
    }

    private fun matchPartnership(): String{
        return "${batsmanOne.score + batsmanTwo.score}(${batsmanOne.ballsPlayed + batsmanTwo.ballsPlayed})"
    }

   override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}