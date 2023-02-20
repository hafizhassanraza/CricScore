package com.example.cricketapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.cricketapp.R
import com.example.cricketapp.databinding.FragmentReadNewsBinding

class ReadNewsFragment : Fragment() {

    private val args : ReadNewsFragmentArgs by navArgs()
    private var _binding : FragmentReadNewsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentReadNewsBinding.inflate(inflater, container, false)

        binding.readNews.webViewClient = WebViewClient()
        binding.readNews.loadUrl(args.url)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}