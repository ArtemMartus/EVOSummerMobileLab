package com.upsage.evosummermobilelab.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

import com.upsage.evosummermobilelab.R
import com.upsage.evosummermobilelab.data.intefaces.OnItemClickListener

class NotesAdapter(noteItemCallback: DiffUtil.ItemCallback<Note>,
                   private val listener: OnItemClickListener)
    : PagedListAdapter<Note, NoteViewHolder>(noteItemCallback) {

    public override fun getItem(position: Int): Note? {
        val list = currentList ?: return null
        return list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false)
        return NoteViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        note?.let { holder.setData(it.date, it.time, it.description) }
    }

    override fun getItemCount(): Int {
        val list = currentList ?: return 0
        return list.size
    }
}
