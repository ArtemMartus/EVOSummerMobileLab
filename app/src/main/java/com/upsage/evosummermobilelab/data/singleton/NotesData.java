package com.upsage.evosummermobilelab.data.singleton;

import android.content.Context;

import androidx.room.Room;

import com.upsage.evosummermobilelab.data.entries.Note;
import com.upsage.evosummermobilelab.data.intefaces.AppDatabase;

import java.util.Date;
import java.util.List;

public class NotesData {
    private static NotesData instance;
    private AppDatabase db;

    private NotesData(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static NotesData initialize(Context context) {
        if (instance == null) {
            synchronized (NotesData.class) {
                if (instance == null)
                    instance = new NotesData(context);
            }
        }
        return instance;
    }

    public static NotesData getInstance() {
        return instance;
    }

    public Note getItem(int position) {
        return db.userDao().loadById(position);
    }

    public void addNote(Note note) {
        db.userDao().insertAll(note);
    }

    public int getSize() {
        return db.userDao().getCount();
    }

    public Note getNewNote() {
        Note note = new Note(new Date(), "");
        addNote(note);
        return note;
    }

    public void delete(int noteId) {
        db.userDao().delete(noteId);
    }

    public void save(Note note) {
        if (note.getDescription().isEmpty()) {
            db.userDao().delete(note.getId());
        } else {
            note.setUpdateDate(new Date());
            if (db.userDao().insertAll(note)[0] == -1)
                db.userDao().update(note);
        }
    }

    public List<Note> getAllNotes() {
        return db.userDao().getAll();
    }

    public List<Note> getLike(String str) {
        return db.userDao().findByDescription("%" + str + "%");
    }
}
