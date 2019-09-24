package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFabAddContact=findViewById(R.id.fab_AddContact);
        mFabAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivity(intent);
            }
        });
        lvContact=findViewById(R.id.lv_contact);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,Edit.class);
                startActivity(intent);
            }
        });
        lvContact = (ListView) findViewById(R.id.lv_contact);
        ArrayList<Contact> arrContact = new ArrayList<>();


        Contact contact1 = new Contact();
        contact1.setName("Nguyen Van A");
        Contact contact2 = new Contact();
        contact2.setName("Nguyen Van B");
        Contact contact3 = new Contact();
        contact3.setName("Nguyen Van C");
        Contact contact4 = new Contact();
        contact4.setName("Nguyen Van D");

        arrContact.add(contact1);
        arrContact.add(contact2);
        arrContact.add(contact3);
        arrContact.add(contact4);

        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.row_listview,arrContact);
        lvContact.setAdapter(customAdapter);
    }
}

