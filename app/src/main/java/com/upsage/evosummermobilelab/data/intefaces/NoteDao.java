package com.upsage.evosummermobilelab.data.intefaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.upsage.evosummermobilelab.data.entries.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("SELECT * FROM note WHERE id = (:noteId) limit 1")
    Note loadById(int noteId);

    @Query("SELECT * FROM note WHERE id IN (:noteIds)")
    List<Note> loadAllByIds(int[] noteIds);

    @Query("SELECT * FROM note WHERE description LIKE :first LIMIT 1")
    Note findByDescription(String first);

    @Insert
    void insertAll(Note... users);

    @Delete
    void delete(Note user);

    @Query("SELECT COUNT(*) FROM note")
    int getCount();

    @Update
    void update(Note note);
}