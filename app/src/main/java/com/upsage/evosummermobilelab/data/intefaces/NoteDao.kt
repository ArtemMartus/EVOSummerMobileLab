package com.upsage.evosummermobilelab.data.intefaces

import androidx.paging.DataSource
import androidx.room.*
import com.upsage.evosummermobilelab.data.Note

@Dao
interface NoteDao {

    @get:Query("select * from note order by update_date desc")
    val allPagedDesc: DataSource.Factory<Int, Note>

    @get:Query("select * from note order by update_date asc")
    val allPagedAsc: DataSource.Factory<Int, Note>

    @Query("SELECT * FROM note WHERE description LIKE :first")
    fun findByDescription(first: String): DataSource.Factory<Int, Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg users: Note): LongArray

    @Delete
    fun delete(id: Note)

    @Update
    suspend fun update(note: Note)

}