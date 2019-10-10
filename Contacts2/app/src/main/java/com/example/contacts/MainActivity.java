package com.example.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton mFabAddContact;
    ListView lvContact;
    ArrayList<Contact> arrContact;
    MyDatabase db;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDatabase(this);
        mFabAddContact = findViewById(R.id.fab_AddContact);
        mFabAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivityForResult(intent, 123);
            }
        });
        lvContact = findViewById(R.id.lv_contact);
        arrContact = new ArrayList<Contact>();
        arrContact = db.getAllContact();
        customAdapter = new CustomAdapter(this, R.layout.row_listview, arrContact);
        lvContact.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getBundleExtra("bundle");
                Contact temp;
                temp = (Contact) bundle.getSerializable("contact");
                db.addContact(temp);
                arrContact.add(temp);
                customAdapter.notifyDataSetChanged();
            }
        }
    }


}


