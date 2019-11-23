package com.cndd.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvWord;
    private WebView webView;
    private TextToSpeech textToSpeech;
    private Button btnSay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_details);

        Word word = null;
        String kind = null;

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("package");
             word = (Word) bundle.getSerializable("word");
             kind = (String) bundle.getSerializable("kind");

            String meaning = "";

             if (kind.equals("en_vi")){
                 MainActivity.databaseAccessEngVie.open();
                 meaning = MainActivity.databaseAccessEngVie.getMeaningById(word.getId());
                 MainActivity.databaseAccessEngVie.close();
             } else {
                 MainActivity.databaseAccessVieEng.open();
                 meaning = MainActivity.databaseAccessVieEng.getMeaningById(word.getId());
                 MainActivity.databaseAccessVieEng.close();
             }

            tvWord = (TextView) findViewById(R.id.tv_word);

            tvWord.setText(word.getWord());

            webView = (WebView) findViewById(R.id.web_view);
            webView.getSettings().setJavaScriptEnabled(true);

            webView.loadData(meaning, "text/html", "UTF-8");
        }

        btnSay = (Button) findViewById(R.id.btn_say);

        final String finalKind = kind;
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = 0;
                    if (finalKind.equals("en_vi")){
                        result = textToSpeech.setLanguage(Locale.US);
                    } else {
                        result = textToSpeech.setLanguage(Locale.ROOT);
                    }
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("textToSpeech", "Language not supported");
                    } else {
                        btnSay.setEnabled(true);
                    }
                } else {
                    Log.e("textToSpeech", "Initialization failed");
                }
            }
        });

        btnSay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        final FloatingActionButton like = findViewById(R.id.fav);
        final FloatingActionButton unlike = findViewById(R.id.disfav);

        final Word finalWord = word;
        like.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                like.setVisibility(View.GONE);
                unlike.setVisibility(View.VISIBLE);
                MainActivity.favoriteDatabase.addWord(finalWord);
            }
        });

        unlike.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                unlike.setVisibility(View.GONE);
                like.setVisibility(View.VISIBLE);
                MainActivity.favoriteDatabase.deleteWord(finalWord);
            }
        });
    }

    private void speak() {
        String text = tvWord.getText().toString();

        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();
    }
}
