package com.example.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton mFabAddContact;
    ListView lvContact;
    ArrayList<Contact> arrContact;
    MyDatabase db;
    CustomAdapter customAdapter;
    EditText mEdtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        //Lấy dữ liệu từ database
        arrContact = new ArrayList<Contact>();
        arrContact = db.getAllContact();
        customAdapter = new CustomAdapter(this, R.layout.row_listview, arrContact);
        lvContact.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();

        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Xác nhận xóa?");
                dialog.setMessage("Are you sure to delete?");
                dialog.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Contact temp = arrContact.get(i);
                        db.deleteContact(temp);
                        arrContact.remove(i);
                        customAdapter.notifyDataSetChanged();
                        Toast toast = Toast.makeText(MainActivity.this,"Đã xóa",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.create().show();
                return false;
            }
        });

        //Search
        mEdtSearch = findViewById(R.id.edt_Search);
        mEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    lvContact.setAdapter(customAdapter);
                } else {
                    int textLength = charSequence.length();
                    ArrayList<Contact> tempArrayList = new ArrayList<Contact>();
                    for (Contact c : arrContact) {
                        if (textLength <= c.getName().length()) {
                            if (c.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                                tempArrayList.add(c);
                            }
                        }
                    }
                    CustomAdapter tempAdapter = new CustomAdapter(MainActivity.this,
                            R.layout.row_listview, tempArrayList);
                    lvContact.setAdapter(tempAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
        if (requestCode == 004) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getBundleExtra("bundle");
                Contact temp;
                temp = (Contact) bundle.getSerializable("contact");
                // Trong database chi so bat dau tu 1
                int test = db.updateContact(temp);
                if (temp.id == test) {
                    Toast toast = Toast.makeText(MainActivity.this, "Đã chỉnh sửa", Toast.LENGTH_SHORT);
                    toast.show();
                }
                // Trong arraylist chi so bat dau tu 0
                arrContact.set(temp.getId() - 1, temp);
                customAdapter.notifyDataSetChanged();
            }
        }
    }


}


