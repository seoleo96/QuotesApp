package com.example.quotesapp.ui.favourites

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quotesapp.R
import com.example.quotesapp.core.App
import com.example.quotesapp.databinding.ActivityMainBinding.inflate
import com.example.quotesapp.databinding.FragmentFavouritesBinding
import com.example.quotesapp.databinding.FragmentHomeBinding
import com.example.quotesapp.ui.home.adapter.QuotesAdapter
import com.example.quotesapp.ui.home.viewmodel.HomeViewModel

class FavouritesFragment : Fragment() {

    private lateinit var favViewModel: FavouritesViewModel
    private var _binding: FragmentFavouritesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        favViewModel = (activity?.application as App).favViewModel

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = binding.rv
        val adapter =
            FavouritesAdapter { text: String, author: String, toSave: Boolean, bool: Boolean ->

//                val action =
//                    HomeFragmentDirections.actionNavigationHomeToQuoteDialogFragment(text, author)
//                findNavController().navigate(action)

            }

        rv.adapter = adapter
        favViewModel.fetchFavourites()
        favViewModel.observe(viewLifecycleOwner, {
            adapter.updateList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}