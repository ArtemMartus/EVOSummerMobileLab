package com.upsage.evosummermobilelab.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.upsage.evosummermobilelab.R;
import com.upsage.evosummermobilelab.data.entries.Note;
import com.upsage.evosummermobilelab.data.intefaces.OnItemClickListener;
import com.upsage.evosummermobilelab.data.singleton.NotesData;
import com.upsage.evosummermobilelab.data.viewholders.NoteViewHolder;

import java.util.List;

public class NotesAdapter extends PagedListAdapter<Note, NoteViewHolder> {

    private final OnItemClickListener listener;
    private List<Note> allNotes;
    private boolean sortByDateAsc = true;

    public NotesAdapter(DiffUtil.ItemCallback<Note> noteItemCallback, OnItemClickListener itemClickListener) {
        super(noteItemCallback);
        listener = itemClickListener;
        if (sortByDateAsc)
            allNotes = NotesData.getInstance().getAllNotesAsc();
        else
            allNotes = NotesData.getInstance().getAllNotesDesc();

    }

    public void setSorting(boolean ascOrder) {
        if (ascOrder != sortByDateAsc) {
            sortByDateAsc = ascOrder;
            if (sortByDateAsc)
                allNotes = NotesData.getInstance().getAllNotesAsc();
            else
                allNotes = NotesData.getInstance().getAllNotesDesc();
            notifyDataSetChanged();
        }
    }

    public Note getItem(int position) {
        return allNotes.get(position);
    }

    public boolean setNotes(List<Note> notes) {
        if (notes != null) {
            this.allNotes = notes;
            notifyDataSetChanged();
            return true;
        }
        return false;
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
