package com.upsage.evosummermobilelab.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upsage.evosummermobilelab.R;
import com.upsage.evosummermobilelab.data.entries.Note;
import com.upsage.evosummermobilelab.data.intefaces.OnItemClickListener;
import com.upsage.evosummermobilelab.data.singleton.NotesData;
import com.upsage.evosummermobilelab.data.viewholders.NoteViewHolder;

public class Notes extends RecyclerView.Adapter<NoteViewHolder> {

    private final OnItemClickListener listener;

    public Notes(OnItemClickListener itemClickListener) {
        listener = itemClickListener;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = NotesData.getInstance().getItem(position + 1);
        if (note != null)
            holder.setData(note.getDate(), note.getTime(), note.getDescription());
    }

    @Override
    public int getItemCount() {
        return NotesData.getInstance().getSize();
    }
}
