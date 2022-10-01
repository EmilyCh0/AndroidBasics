package com.example.roomword

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter: ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {

    class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val itemTv: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?){
            itemTv.text = text
        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item.word)
    }

    class WordsComparator: DiffUtil.ItemCallback<Word>(){
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }
    }
}