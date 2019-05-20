package com.upsage.evosummermobilelab.data.intefaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.upsage.evosummermobilelab.data.entries.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("SELECT * FROM note WHERE id = :noteId")
    Note loadById(int noteId);

    @Query("SELECT * FROM note WHERE id IN (:noteIds)")
    List<Note> loadAllByIds(int[] noteIds);

    @Query("SELECT * FROM note WHERE description LIKE :first")
    Note findByDescription(String first);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertAll(Note... users);

    @Query("DELETE FROM note WHERE id = :id")
    void delete(int id);

    @Query("SELECT COUNT(*) FROM note")
    int getCount();

    @Update
    void update(Note note);
}