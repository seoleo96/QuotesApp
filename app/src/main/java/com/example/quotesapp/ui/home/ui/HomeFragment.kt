package com.example.quotesapp.ui.home.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quotesapp.R
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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = binding.rv
        val adapter = QuotesAdapter {text: String, author : String, toSave: Boolean, bool  :Boolean ->
            if (bool){
                val action =
                    HomeFragmentDirections.actionNavigationHomeToQuoteDialogFragment(text, author)
                findNavController().navigate(action)
            }else{
                homeViewModel.saveAndDeleteFavorites(toSave, text)
                Toast.makeText(requireContext(), "$text, $author, $toSave", Toast.LENGTH_SHORT).show()
            }
        }

        rv.adapter = adapter
        homeViewModel.fetchQuotes("")
        homeViewModel.observe(viewLifecycleOwner, {
            adapter.updateList(it)
        })

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Pair<String, Boolean>>(
            "author")
            ?.observe(viewLifecycleOwner) {
                if (!it.second) {
                    homeViewModel.updateQuote(isCheck = it.second, author = it.first)
                } else {
                    homeViewModel.fetchQuotes(it.first)
                    homeViewModel.updateQuote(isCheck = it.second, author = it.first)
                    homeViewModel.observe(viewLifecycleOwner, { it ->
                        adapter.updateList(it)
                    })
                }
            }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.authors, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.author_sort -> findNavController().navigate(R.id.action_navigation_home_to_authorsDialogFragment)
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}