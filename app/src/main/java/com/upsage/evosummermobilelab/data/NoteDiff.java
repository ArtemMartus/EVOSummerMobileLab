package com.upsage.evosummermobilelab.data;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.upsage.evosummermobilelab.data.entries.Note;

public class NoteDiff extends DiffUtil.ItemCallback<Note> {
    @Override
    public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getDescription().equals(newItem.getDescription());
    }
}
