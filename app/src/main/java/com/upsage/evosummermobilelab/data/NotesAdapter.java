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

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private final OnItemClickListener listener;
    private final List<Note> allNotes;

    public NotesAdapter(OnItemClickListener itemClickListener) {
        listener = itemClickListener;
        allNotes = NotesData.getInstance().getAllNotes();
    }


    public Note getItem(int position) {
        return allNotes.get(position);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = allNotes.get(position);
        if (note != null)
            holder.setData(note.getDate(), note.getTime(), note.getDescription());
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }
}
