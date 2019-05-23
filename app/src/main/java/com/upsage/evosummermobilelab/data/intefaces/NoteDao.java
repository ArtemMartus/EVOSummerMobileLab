package com.upsage.evosummermobilelab.data.intefaces;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.upsage.evosummermobilelab.data.entries.Note;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note WHERE id = :noteId")
    Note loadById(int noteId);

    @Query("SELECT * FROM note WHERE description LIKE :first")
    DataSource.Factory<Integer, Note> findByDescription(String first);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertAll(Note... users);

    @Delete
    void delete(Note id);

    @Update
    void update(Note note);

    @Query("select * from note order by update_date desc")
    DataSource.Factory<Integer, Note> getAllPagedDesc();

    @Query("select * from note order by update_date asc")
    DataSource.Factory<Integer, Note> getAllPagedAsc();

}