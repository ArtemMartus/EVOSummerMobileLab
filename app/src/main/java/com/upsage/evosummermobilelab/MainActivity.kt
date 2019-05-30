package com.upsage.evosummermobilelab

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.upsage.evosummermobilelab.data.Note
import com.upsage.evosummermobilelab.data.NoteDiff
import com.upsage.evosummermobilelab.data.NotesAdapter
import com.upsage.evosummermobilelab.data.intefaces.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    enum class Mode { Ascending, Descending }

    private var viewModel: NotesViewModel? = null
    private var notesAdapter: NotesAdapter? = null
    private var data: LiveData<PagedList<Note>>? = null
    private var showMode: Mode = Mode.Ascending


    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel = NotesApplication.viewModel

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        notesAdapter = NotesAdapter(NoteDiff(), this)
        notesRecyclerView.layoutManager = LinearLayoutManager(this)
        notesRecyclerView.addItemDecoration(DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL))

        mainSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            fun doSearch(str: String): Boolean {
                goSearch(str)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return doSearch(query)
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return doSearch(newText)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (showMode == Mode.Ascending)
            goAsc()
        else
            goDesc()

    }

    private fun goAsc() {

        data = viewModel?.getAsc()
        data?.let {
            it.observe(this, Observer { notes ->
                notes?.let { notesAdapter?.submitList(notes) }
            })
        }

        notesRecyclerView?.adapter = notesAdapter
    }

    private fun goDesc() {

        data = viewModel?.getDesc()
        data?.let {
            it.observe(this, Observer { notes ->
                notes?.let { notesAdapter?.submitList(notes) }
            })
        }

        notesRecyclerView?.adapter = notesAdapter
    }

    internal fun goSearch(str: String) {
        data = viewModel?.goSearch(str)
        data?.let {
            it.observe(this, Observer { notes ->
                notes?.let { notesAdapter?.submitList(notes) }
            })
        }

        notesRecyclerView?.adapter = notesAdapter

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.descendingSort -> {
                showMode = Mode.Descending
                goDesc()
                return true
            }
            R.id.ascendingSort -> {
                showMode = Mode.Ascending
                goAsc()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun addNoteClick(fabView: View) =
            goToDetailsActivity(null)


    private fun goToDetailsActivity(note: Note?) {
        val intent = Intent(this, NoteDetailsActivity::class.java)
        viewModel?.selectedNote = note
        startActivity(intent)
    }

    override fun OnItemClick(view: View, position: Int) =
            goToDetailsActivity(notesAdapter?.getItem(position))


}
