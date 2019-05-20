package com.upsage.evosummermobilelab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upsage.evosummermobilelab.activities.NoteDetailsActivity;
import com.upsage.evosummermobilelab.data.NotesAdapter;
import com.upsage.evosummermobilelab.data.intefaces.OnItemClickListener;
import com.upsage.evosummermobilelab.data.singleton.NotesData;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NotesData.initialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView notesRecyclerView = findViewById(R.id.notesRecyclerView);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NotesAdapter(this);
        notesRecyclerView.setAdapter(notesAdapter);
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