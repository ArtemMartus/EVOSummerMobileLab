package com.upsage.evosummermobilelab.data

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.upsage.evosummermobilelab.R
import com.upsage.evosummermobilelab.data.intefaces.OnItemClickListener

class NoteViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {

    private val dateTextView: TextView
    private val timeTextView: TextView
    private val noteTextView: TextView

    init {
        itemView.setOnClickListener { v ->
            listener?.OnItemClick(v, adapterPosition)
        }
        dateTextView = itemView.findViewById(R.id.dateTextView)
        timeTextView = itemView.findViewById(R.id.timeTextView)
        noteTextView = itemView.findViewById(R.id.noteShortDescriptionTextView)
    }

    fun setData(creationDate: String, creationTime: String, text: String) {
        dateTextView.text = creationDate
        timeTextView.text = creationTime
        val length = Math.min(100, text.length)
        noteTextView.text = text.substring(0, length)
    }
}
