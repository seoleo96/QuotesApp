package com.example.quotesapp.ui.authors


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quotesapp.core.App
import com.example.quotesapp.databinding.FragmentAuthorsDialogListDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AuthorsDialogFragment : BottomSheetDialogFragment() {

    private lateinit var authorViewModel: AuthorsViewModel

    private var _binding: FragmentAuthorsDialogListDialogBinding? = null

    private val binding get() = _binding!!

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        ItemAdapter { author, bool ->
            searchAuthor(author, bool)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        authorViewModel = (activity?.application as App).authorViewModel
        _binding = FragmentAuthorsDialogListDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.list.layoutManager = GridLayoutManager(context, 2)
        binding.list.adapter = adapter
        authorViewModel.fetchAuthors()
        updateList()

        val searchView = binding.searchView
        searchView.onQueryTextChanged {
            authorViewModel.search(it)
            updateList()
        }
    }

    private fun updateList() {
        authorViewModel.observe(this, {
            adapter.updateList(it)
        })
    }

    private fun searchAuthor(author: String, bool: Boolean) {
        if (bool) {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("author",
                Pair(author, bool))
            this.dismiss()
        } else {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("author",
                Pair(author, bool))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}