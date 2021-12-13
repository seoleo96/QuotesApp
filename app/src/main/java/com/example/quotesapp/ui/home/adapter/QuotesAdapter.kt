package com.example.quotesapp.ui.home.adapter

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


class QuotesAdapter(
    private val itemClickListener: (text: String, author: String, toSave: Boolean, bool: Boolean) -> Unit,
) :
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
            else -> 4
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
        val bind: QuoteUIState = listUIState[position]
        holder.bind(bind)
    }


    override fun getItemCount(): Int {
        return listUIState.size
    }


    abstract class QuotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        open fun bind(quoteUIState: QuoteUIState) {}

        class Progress(view: View) : QuotesViewHolder(view)

        class Base(
            private val view: View,
            private val itemClickListener: (text: String, author: String,toSave: Boolean, bool: Boolean) -> Unit,
        ) :
            QuotesViewHolder(view) {
            private val mText = itemView.findViewById<TextView>(R.id.text_quote)
            private val mAuthor = itemView.findViewById<TextView>(R.id.author)
            private val cv = itemView.findViewById<CardView>(R.id.cv)
            private val blue = itemView.findViewById<ImageView>(R.id.favourite_imageview)
            override fun bind(quote: QuoteUIState) {
                quote.map(object : ContentMapper {
                    override fun map(text: String, author: String, toSave: Boolean) {
                        mText.text = text
                        mAuthor.text = author
                        if (toSave){
                            blue.setBackgroundResource(R.drawable.star_full_black)

                        }else{
                            blue.setBackgroundResource(R.drawable.star_full_blue)
                        }
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

        class Fail(view: View) : QuotesViewHolder(view) {
            private val textView = itemView.findViewById<TextView>(R.id.error_text)
            override fun bind(uiState: QuoteUIState) {
                uiState.map(object : ContentMapper {
                    override fun map(text: String, author: String, toSave: Boolean) {
                        if (author == "1")
                            textView.text = text
                    }
                })
            }
        }
    }
}