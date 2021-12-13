package com.example.quotesapp.ui.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.ui.QuoteUIState
import com.example.quotesapp.ui.home.mapper.ContentMapper


class FavouritesAdapter(
    private val itemClickListener: (text: String, author: String, toSave: Boolean, bool: Boolean) -> Unit,
) :
    RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {

    private val listUIState = arrayListOf<FavouritesUIState>()
    fun updateList(list: List<FavouritesUIState>) {
        listUIState.clear()
        listUIState.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (listUIState[position]) {
            is FavouritesUIState.Success -> 1
            is FavouritesUIState.Progress -> 2
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FavouritesViewHolder {
        return when (viewType) {
            1 -> FavouritesViewHolder.Base(R.layout.quote_row.makeView(parent), itemClickListener)
            else -> FavouritesViewHolder.Progress(R.layout.progress_row.makeView(parent))

        }

    }

    private fun Int.makeView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(this, parent, false)

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val bind: FavouritesUIState = listUIState[position]
        holder.bind(bind)
    }


    override fun getItemCount(): Int {
        return listUIState.size
    }


    abstract class FavouritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        open fun bind(quoteUIState: FavouritesUIState) {}

        class Progress(view: View) : FavouritesViewHolder(view)

        class Base(
            private val view: View,
            private val itemClickListener: (text: String, author: String,toSave: Boolean, bool: Boolean) -> Unit,
        ) :
            FavouritesViewHolder(view) {
            private val mText = itemView.findViewById<TextView>(R.id.text_quote)
            private val mAuthor = itemView.findViewById<TextView>(R.id.author)
            private val cv = itemView.findViewById<CardView>(R.id.cv)
            private val blue = itemView.findViewById<ImageView>(R.id.favourite_imageview)
            override fun bind(fav: FavouritesUIState) {
                fav.map(object : FavouritesContentMapper {
                    override fun map(text: String, author: String, toSave: Boolean) {
                        if (toSave){
                            blue.setBackgroundResource(R.drawable.star_full_black)

                        }else{
                            blue.setBackgroundResource(R.drawable.star_full_blue)
                        }
                        mText.text = text
                        mAuthor.text = author

                        cv.setOnClickListener {
                            itemClickListener.invoke(text, author, false, true)
                        }

                        blue.setOnClickListener {
                            if (it.tag.toString() == "1") {
                                it.tag = "2"
                                it.setBackgroundResource(R.drawable.star_full_black)
                                itemClickListener.invoke(text, author, true, false)
                            } else {
                                it.tag = "1"
                                it.setBackgroundResource(R.drawable.star_full_blue)
                                itemClickListener.invoke(text, author, false, false)
                            }
                        }
                    }
                })
            }

        }

        class Fail(view: View) : FavouritesViewHolder(view) {
            private val textView = itemView.findViewById<TextView>(R.id.error_text)
            override fun bind(uiState: FavouritesUIState) {
                uiState.map(object : FavouritesContentMapper {
                    override fun map(text: String, author: String, toSave: Boolean) {
                        if (author == "1")
                            textView.text = text
                    }
                })
            }
        }
    }
}