package com.upsage.evosummermobilelab;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upsage.evosummermobilelab.activities.NoteDetailsActivity;
import com.upsage.evosummermobilelab.data.NoteDiff;
import com.upsage.evosummermobilelab.data.NotesAdapter;
import com.upsage.evosummermobilelab.data.entries.Note;
import com.upsage.evosummermobilelab.data.intefaces.OnItemClickListener;
import com.upsage.evosummermobilelab.data.singleton.NotesData;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private NotesAdapter notesAdapter;
    private SearchView searchView;
    private LiveData<PagedList<Note>> notePagedLiveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NotesData.initialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView notesRecyclerView = findViewById(R.id.notesRecyclerView);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        DataSource.Factory<Integer, Note> factory = NotesData.getInstance().getAllPaged();
        LivePagedListBuilder<Integer, Note> pagedListBuilder = new LivePagedListBuilder<>(factory, 20);

        notesAdapter = new NotesAdapter(new NoteDiff(), this);
        notePagedLiveList = pagedListBuilder.build();
        notePagedLiveList.observe(this, notes -> {
            if (notes != null)
                notesAdapter.submitList(notes);
        });

        notesRecyclerView.setAdapter(notesAdapter);


        searchView = findViewById(R.id.mainSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            boolean doSearch(String str) {
                List<Note> notes = NotesData.getInstance().getLike(str);
                return notesAdapter.setNotes(notes);
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return doSearch(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return doSearch(newText);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ascendingSort:
                notesAdapter.setSorting(true);
                return true;
            case R.id.descendingSort:
                notesAdapter.setSorting(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void addNoteClick(View fabView) {
        goToDetailsActivity(null);
    }

    void goToDetailsActivity(Integer noteId) {
        Intent intent = new Intent(this, NoteDetailsActivity.class);
        if (noteId != null)
            intent.putExtra("noteId", noteId);
        startActivity(intent);
        finish();
    }

    @Override
    public void OnItemClick(View view, int position) {
        goToDetailsActivity(notesAdapter.getItem(position).getId());
    }

}
