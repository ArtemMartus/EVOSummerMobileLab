package com.upsage.evosummermobilelab

import android.app.Application

class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        viewModel = NotesViewModel(this)
    }

    companion object {
        var viewModel: NotesViewModel? = null
            private set
    }
}