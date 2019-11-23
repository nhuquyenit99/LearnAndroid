package com.cndd.mydictionary;

import android.os.Bundle;

import com.cndd.mydictionary.database.DatabaseAccessEngVie;
import com.cndd.mydictionary.database.DatabaseAccessVieEng;
import com.cndd.mydictionary.database.FavoriteDatabase;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cndd.mydictionary.database.MyWordsDatabase;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static DatabaseAccessEngVie databaseAccessEngVie;
    public static DatabaseAccessVieEng databaseAccessVieEng;
    public static List<Word> en_vi, vi_en;
    public static FavoriteDatabase favoriteDatabase;
    public static MyWordsDatabase myWordsDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_en_en, R.id.nav_vi_en,
                R.id.nav_favorite, R.id.nav_your_words, R.id.nav_share, R.id.nav_help)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        favoriteDatabase = new FavoriteDatabase(this);
        myWordsDatabase = new MyWordsDatabase(this);

        //Load data
        if (savedInstanceState == null){
            MainActivity.databaseAccessEngVie = DatabaseAccessEngVie.getInstance(this);
            MainActivity.databaseAccessEngVie.open();
            en_vi = MainActivity.databaseAccessEngVie.getWords();
            MainActivity.databaseAccessEngVie.close();
        }

        if (savedInstanceState == null){
            MainActivity.databaseAccessVieEng = DatabaseAccessVieEng.getInstance(this);
            MainActivity.databaseAccessVieEng.open();
            vi_en = MainActivity.databaseAccessVieEng.getWords();
            MainActivity.databaseAccessVieEng.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
