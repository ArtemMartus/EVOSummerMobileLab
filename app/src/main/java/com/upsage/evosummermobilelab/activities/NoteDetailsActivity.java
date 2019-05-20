package com.upsage.evosummermobilelab.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.upsage.evosummermobilelab.MainActivity;
import com.upsage.evosummermobilelab.R;
import com.upsage.evosummermobilelab.data.entries.Note;
import com.upsage.evosummermobilelab.data.singleton.NotesData;

public class NoteDetailsActivity extends AppCompatActivity {

    private Note note;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        int noteId = getIntent().getIntExtra("noteId", -1);
        if (noteId != -1)
            note = NotesData.getInstance().getItem(noteId);

        if (note == null)
            note = NotesData.getInstance().getNewNote();

        editText = findViewById(R.id.noteEditText);
        editText.setText(note.getDescription());

    }

    private void goMain() {
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            note.setDescription(text);
            NotesData.getInstance().save(note);
        } else if (note.getId() != null)
            NotesData.getInstance().delete(note.getId());

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        goMain();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteNoteMenuItem:
                editText.getText().clear();
                goMain();
                return true;
            case R.id.shareMenuItem:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editText.getText());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
