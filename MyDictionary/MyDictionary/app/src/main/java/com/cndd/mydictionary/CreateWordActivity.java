package com.cndd.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cndd.mydictionary.ui.en_vi.EnglishToVietnameseFragment;

public class CreateWordActivity extends AppCompatActivity {

    private EditText edtWord, edtContent;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_word);

        edtWord = (EditText) findViewById(R.id.edt_word);
        edtContent = (EditText) findViewById(R.id.edt_content);
        save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.databaseAccessEngVie.open();
                int id = (int) (MainActivity.databaseAccessEngVie.getCount() + 1);
                Word word = new Word(id, edtWord.getText().toString(), edtContent.getText().toString());
                MainActivity.databaseAccessEngVie.addWord(word);
                MainActivity.databaseAccessEngVie.close();

                Intent intent = new Intent(CreateWordActivity.this, EnglishToVietnameseFragment.class);
                startActivity(intent);
            }
        });
    }
}
