package com.upsage.evosummermobilelab.data.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upsage.evosummermobilelab.R;
import com.upsage.evosummermobilelab.data.intefaces.OnItemClickListener;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private final TextView dateTextView;
    private final TextView timeTextView;
    private final TextView noteTextView;

    public NoteViewHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        itemView.setOnClickListener((v -> {
            if (listener != null)
                listener.OnItemClick(v, getAdapterPosition());
        }));
        dateTextView = itemView.findViewById(R.id.dateTextView);
        timeTextView = itemView.findViewById(R.id.timeTextView);
        noteTextView = itemView.findViewById(R.id.noteShortDescriptionTextView);
    }

    public void setData(String creationDate, String creationTime, String text) {
        dateTextView.setText(creationDate);
        timeTextView.setText(creationTime);
        int length = Math.min(100, text.length());
        noteTextView.setText(text.substring(0, length));
    }
}
