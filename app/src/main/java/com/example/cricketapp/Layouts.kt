package com.example.cricketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cricketapp.databinding.LayoutsBinding

class Layouts : AppCompatActivity() {

    private lateinit var binding: LayoutsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        scoringCardClickListeners()
        crossClickListeners()

    }

    private fun crossClickListeners() {

        binding.closeByes.setOnClickListener {
            binding.scoringByes.visibility = View.GONE
            binding.scoringTable.visibility = View.VISIBLE
        }

        binding.closeLB.setOnClickListener {
            binding.scoringLegByes.visibility = View.GONE
            binding.scoringTable.visibility = View.VISIBLE
        }
        binding.closeWickets.setOnClickListener {
            binding.wicket.visibility = View.GONE
            binding.scoringTable.visibility = View.VISIBLE
        }
        binding.closeWide.setOnClickListener {
            binding.scoringWide.visibility = View.GONE
            binding.scoringTable.visibility = View.VISIBLE
        }
        binding.closeBonusRunAdd.setOnClickListener {
            binding.bonusRunsAdd.visibility = View.GONE
            binding.scoringTable.visibility = View.VISIBLE
        }
        binding.closeBonusRunMinus.setOnClickListener {
            binding.bonusRunsMinus.visibility = View.GONE
            binding.scoringTable.visibility = View.VISIBLE
        }
        binding.closeMoreRuns.setOnClickListener {
            binding.moreRuns.visibility = View.GONE
            binding.scoringTable.visibility = View.VISIBLE
        }
        binding.closeNoBall.setOnClickListener {
            binding.scoringNoBall.visibility = View.GONE
            binding.scoringTable.visibility = View.VISIBLE
        }
        binding.closeScoringMore.setOnClickListener {
            binding.scoringMore.visibility = View.GONE
            binding.scoringTable.visibility = View.VISIBLE
        }
    }

    private fun scoringCardClickListeners() {

        binding.tvLB.setOnClickListener {
            binding.scoringTable.visibility = View.GONE
            binding.scoringLegByes.visibility = View.VISIBLE

        }
        binding.tvNB.setOnClickListener {
            binding.scoringTable.visibility = View.GONE
            binding.scoringNoBall.visibility = View.VISIBLE

        }
        binding.tvBye.setOnClickListener {
            binding.scoringTable.visibility = View.GONE
            binding.scoringByes.visibility = View.VISIBLE

        }
        binding.tvMore.setOnClickListener {
            binding.scoringTable.visibility = View.GONE
            binding.moreRuns.visibility = View.VISIBLE

        }
        binding.tvScoreMore.setOnClickListener {
            binding.scoringTable.visibility = View.GONE
            binding.scoringMore.visibility = View.VISIBLE

        }
        binding.tvBonus.setOnClickListener {
            binding.scoringTable.visibility = View.GONE
            binding.bonusRunsAdd.visibility = View.VISIBLE

        }
        binding.tvOut.setOnClickListener{
            binding.scoringTable.visibility = View.GONE
            binding.wicket.visibility = View.VISIBLE

        }
    }
}