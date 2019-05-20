package com.upsage.evosummermobilelab.data.intefaces;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.upsage.evosummermobilelab.data.entries.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao userDao();
}