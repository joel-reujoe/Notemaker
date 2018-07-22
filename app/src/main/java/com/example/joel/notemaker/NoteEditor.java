package com.example.joel.notemaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditor extends AppCompatActivity {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        EditText editText=(EditText)findViewById(R.id.editText);
        Intent intent=getIntent();

        noteId=intent.getIntExtra("NoteID",-1);
        if(noteId!=-1)
        {
            editText.setText(Note.notes.get(noteId));
        }
        else
        {
            Note.notes.add("");
            noteId=Note.notes.size()-1;
            Note.arrayAdapter.notifyDataSetChanged();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                Note.notes.set(noteId,String.valueOf(charSequence));
                Note.arrayAdapter.notifyDataSetChanged();
                SharedPreferences preferences=getApplicationContext().getSharedPreferences("com.example.joel.notemaker", Context.MODE_PRIVATE);
                HashSet<String> set =new HashSet<String>(Note.notes);
                preferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
