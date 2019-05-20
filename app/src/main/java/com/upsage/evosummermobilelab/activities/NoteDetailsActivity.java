package com.upsage.evosummermobilelab.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.upsage.evosummermobilelab.MainActivity;
import com.upsage.evosummermobilelab.R;
import com.upsage.evosummermobilelab.data.entries.Note;
import com.upsage.evosummermobilelab.data.singleton.NotesData;

public class NoteDetailsActivity extends AppCompatActivity {

    Note note;
    EditText editText;

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

    @Override
    protected void onStop() {
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            note.setDescription(text);
            NotesData.getInstance().save(note);
        } else
            NotesData.getInstance().delete(note);
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
