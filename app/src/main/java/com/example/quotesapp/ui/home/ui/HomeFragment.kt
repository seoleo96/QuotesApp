package com.example.quotesapp.ui.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quotesapp.core.App
import com.example.quotesapp.databinding.FragmentHomeBinding
import com.example.quotesapp.ui.home.adapter.QuotesAdapter
import com.example.quotesapp.ui.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        homeViewModel = (activity?.application as App).homeViewModel

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = binding.rv
        val adapter = QuotesAdapter { _, _ -> }

        rv.adapter = adapter
        homeViewModel.fetchQuotes()
        homeViewModel.observe(viewLifecycleOwner, {
            adapter.updateList(it)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}