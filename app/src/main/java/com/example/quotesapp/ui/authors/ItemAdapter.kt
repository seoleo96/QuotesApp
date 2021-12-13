package com.example.quotesapp.ui.authors


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R

class ItemAdapter(
    private val authorItemClickListener: (author: String, bool: Boolean) -> Unit
) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val listUIState = arrayListOf<AuthorUIState>()
    fun updateList(list: List<AuthorUIState>) {
        listUIState.clear()
        listUIState.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (listUIState[position]) {
            is AuthorUIState.Success -> 1
            is AuthorUIState.Progress -> 2
        }
    }

    private fun Int.makeView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(this, parent, false)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            1 -> ViewHolder.Base(R.layout.author_row.makeView(parent), authorItemClickListener)
            else -> ViewHolder.Progress(R.layout.progress_row.makeView(parent))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUIState[position])
    }

    override fun getItemCount(): Int {
        return listUIState.size
    }

    abstract class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        open fun bind(uiState: AuthorUIState) = Unit

        class Progress(private val view: View) : ItemAdapter.ViewHolder(view)

        class Base(
            private val view: View,
            private val itemClickListener: (author: String, bool: Boolean) -> Unit,
        ) :
            ItemAdapter.ViewHolder(view) {
            private val mAuthor = view.findViewById<TextView>(R.id.author_text)
            override fun bind(uiState: AuthorUIState) {
                uiState.map(object : AuthorContentMapper {
                    override fun map(author: String, isCheck: Boolean) {
                        if (isCheck) {
                            mAuthor.setTextColor(Color.BLUE)
                        } else {
                            mAuthor.setTextColor(Color.BLACK)
                        }
                        mAuthor.text = author
                        mAuthor.setOnClickListener {
                            if (isCheck) {
                                mAuthor.setTextColor(Color.BLACK)
                                itemClickListener.invoke(author, false)
                            } else {
                                mAuthor.setTextColor(Color.BLUE)
                                itemClickListener.invoke(author, true)
                            }
                        }
                    }
                })
            }
        }

    }

}
