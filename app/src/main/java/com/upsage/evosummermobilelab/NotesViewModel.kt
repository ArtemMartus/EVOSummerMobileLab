package com.upsage.evosummermobilelab


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.upsage.evosummermobilelab.data.Note
import com.upsage.evosummermobilelab.data.NotesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application)
    : AndroidViewModel(application) {

    init {
        NotesData.initialize(application)
    }

    var selectedNote: Note? = null

    fun getAsc(): LiveData<PagedList<Note>>? {
        val factory = NotesData.instance!!.allPagedAsc
        return LivePagedListBuilder(factory, 20).build()

    }

    fun getDesc(): LiveData<PagedList<Note>>? {
        val factory = NotesData.instance!!.allPagedDesc
        return LivePagedListBuilder(factory, 20).build()

    }

    fun goSearch(str: String): LiveData<PagedList<Note>>? {
        val factory = NotesData.instance!!.getLike(str)
        return LivePagedListBuilder(factory, 20).build()
    }

    fun getNewNote(): Note = NotesData.instance!!.newNote

    fun save(note: Note) = viewModelScope.launch(Dispatchers.IO) { NotesData.instance!!.save(note) }

    fun delete(note: Note) = NotesData.instance!!.delete(note)

}
