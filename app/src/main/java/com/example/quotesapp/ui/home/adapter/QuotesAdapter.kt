package com.example.quotesapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.ui.QuoteUIState
import com.example.quotesapp.ui.home.mapper.ContentMapper


class QuotesAdapter(private val itemClickListener: (text: String, author: String) -> Unit) :
    RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>() {

    private val listUIState = arrayListOf<QuoteUIState>()
    fun updateList(list: List<QuoteUIState>) {
        listUIState.clear()
        listUIState.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (listUIState[position]) {
            is QuoteUIState.Fail -> 0
            is QuoteUIState.Success -> 1
            is QuoteUIState.Progress -> 2
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): QuotesViewHolder {
        return when (viewType) {
            0 -> QuotesViewHolder.Fail(R.layout.error_row.makeView(parent))
            1 -> QuotesViewHolder.Base(R.layout.quote_row.makeView(parent), itemClickListener)
            else -> QuotesViewHolder.Progress(R.layout.progress_row.makeView(parent))

        }

    }

    private fun Int.makeView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(this, parent, false)

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.bind(listUIState[position])
    }


    override fun getItemCount(): Int {
        return listUIState.size
    }


    abstract class QuotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        open fun bind(quoteUIState: QuoteUIState) {}

        class Progress(view: View) : QuotesViewHolder(view)

        class Base(
            private val view: View,
            private val itemClickListener: (text: String, author: String) -> Unit,
        ) :
            QuotesViewHolder(view) {
            private val mText = itemView.findViewById<TextView>(R.id.text_quote)
            private val mAuthor = itemView.findViewById<TextView>(R.id.author)
            private val cv = itemView.findViewById<CardView>(R.id.cv)
            override fun bind(quote: QuoteUIState) {
                quote.map(object : ContentMapper() {
                    override fun map(text: String, author: String) {
                        mText.text = text
                        mAuthor.text = author
                    }
                })
            }

        }

        class Fail(view: View) : QuotesViewHolder(view) {
            private val textView = itemView.findViewById<TextView>(R.id.error_text)
            override fun bind(uiState: QuoteUIState) {
                uiState.map(object : ContentMapper() {
                    override fun map(text: String, author: String) {
                        if (author == "1")
                            textView.text = text
                    }
                })
            }
        }
    }
}