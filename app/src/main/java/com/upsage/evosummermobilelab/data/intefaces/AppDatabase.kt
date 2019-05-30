package com.upsage.evosummermobilelab.data.intefaces

import androidx.room.Database
import androidx.room.RoomDatabase

import com.upsage.evosummermobilelab.data.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): NoteDao
}