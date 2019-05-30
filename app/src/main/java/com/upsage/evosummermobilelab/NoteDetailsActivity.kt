package com.upsage.evosummermobilelab

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.upsage.evosummermobilelab.data.Note
import kotlinx.android.synthetic.main.activity_note_details.*

class NoteDetailsActivity : AppCompatActivity() {

    private var note: Note? = null
    private var viewModel: NotesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        viewModel = NotesApplication.viewModel

        note = viewModel?.selectedNote ?: viewModel?.getNewNote()

        noteEditText.setText(note?.description)

    }

    private fun goMain() {
        val text = noteEditText.text.toString()
        note?.let {
            if (text.isNotEmpty()) {
                it.description = text
                viewModel?.save(it)

            } else {
                it.id.let { _ -> viewModel?.delete(it) }
            }
        }
    }

    override fun onPause() {
        goMain()
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteNoteMenuItem -> {
                noteEditText.text.clear()
                goMain()
                return true
            }
            R.id.shareMenuItem -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, noteEditText.text)
                    type = "text/plain"
                }

                startActivity(sendIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
