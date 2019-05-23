package com.upsage.evosummermobilelab.data.singleton;

import android.content.Context;

import androidx.paging.DataSource;
import androidx.room.Room;

import com.upsage.evosummermobilelab.data.entries.Note;
import com.upsage.evosummermobilelab.data.intefaces.AppDatabase;

import java.util.Date;


public class NotesData {
    private static NotesData instance;
    private final AppDatabase db;

    private NotesData(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static void initialize(Context context) {
        if (instance == null) {
            synchronized (NotesData.class) {
                if (instance == null)
                    instance = new NotesData(context);
            }
        }
    }

    public static NotesData getInstance() {
        return instance;
    }

    public Note getItem(int position) {
        return db.userDao().loadById(position);
    }

//    private void addNote(Note note) {
//        db.userDao().insertAll(note);
//    }

    public Note getNewNote() {
        Note note = new Note(new Date(), "");
        //addNote(note); should not add empty note to db
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

    public DataSource.Factory<Integer, Note> getLike(String str) {
        return db.userDao().findByDescription("%" + str + "%");
    }

    public DataSource.Factory<Integer, Note> getAllPagedAsc() {
        return db.userDao().getAllPagedAsc();
    }

    public DataSource.Factory<Integer, Note> getAllPagedDesc() {
        return db.userDao().getAllPagedDesc();
    }
}
