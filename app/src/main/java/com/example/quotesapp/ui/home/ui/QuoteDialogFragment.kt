package com.example.quotesapp.ui.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.quotesapp.databinding.FragmentQuoteDialogListDialogItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class QuoteDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentQuoteDialogListDialogItemBinding? = null
    private val navArgs by navArgs<QuoteDialogFragmentArgs>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentQuoteDialogListDialogItemBinding.inflate(inflater, container, false)
        binding.apply {
            textAuthor.text = navArgs.author
            textQuote.text = navArgs.text
        }
        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}