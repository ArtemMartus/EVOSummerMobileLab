package com.upsage.evosummermobilelab.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.upsage.evosummermobilelab.R;
import com.upsage.evosummermobilelab.data.entries.Note;
import com.upsage.evosummermobilelab.data.intefaces.OnItemClickListener;
import com.upsage.evosummermobilelab.data.viewholders.NoteViewHolder;

public class NotesAdapter extends PagedListAdapter<Note, NoteViewHolder> {

    private final OnItemClickListener listener;

    public NotesAdapter(DiffUtil.ItemCallback<Note> noteItemCallback, OnItemClickListener itemClickListener) {
        super(noteItemCallback);
        listener = itemClickListener;
    }

    public Note getItem(int position) {
        PagedList<Note> list = getCurrentList();
        if (list == null)
            return null;
        return list.get(position);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = getItem(position);
        if (note != null)
            holder.setData(note.getDate(), note.getTime(), note.getDescription());
    }

    @Override
    public int getItemCount() {
        PagedList<?> list = getCurrentList();
        if (list == null)
            return 0;
        return list.size();
    }
}
