package com.upsage.evosummermobilelab.data

import android.content.Context
import androidx.paging.DataSource
import androidx.room.Room
import com.upsage.evosummermobilelab.data.intefaces.AppDatabase
import java.util.*


class NotesData private constructor(context: Context) {
    private val db: AppDatabase = Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java, "database")
            .build()


    val newNote: Note
        get() = Note(Date(), "")

    val allPagedAsc: DataSource.Factory<Int, Note>
        get() = db.userDao().allPagedAsc

    val allPagedDesc: DataSource.Factory<Int, Note>
        get() = db.userDao().allPagedDesc

    fun delete(note: Note) {
        db.userDao().delete(note)
    }

    suspend fun save(note: Note) {
        if (note.description.isEmpty()) {
            db.userDao().delete(note)
        } else {
            note.updateDate = Date()
            if (db.userDao().insertAll(note)[0] == -1L)
                db.userDao().update(note)
        }
    }

    fun getLike(str: String): DataSource.Factory<Int, Note> {
        return db.userDao().findByDescription("%$str%")
    }

    companion object {
        var instance: NotesData? = null
            private set

        fun initialize(context: Context) {
            if (instance == null) {
                synchronized(NotesData::class.java) {
                    if (instance == null)
                        instance = NotesData(context)
                }
            }
        }
    }
}
