package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Edit extends AppCompatActivity {
    ImageButton mIbCancelEdit;
    ImageButton mIbDoneEdit;
    EditText edtName;
    EditText edtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mIbCancelEdit=findViewById(R.id.btn_CancelEdit);
        mIbCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        mIbDoneEdit=findViewById(R.id.btn_DoneEdit);
        mIbDoneEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        edtName=findViewById(R.id.edt_Name);
        edtPhone=findViewById(R.id.edt_Phone);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("package");
        Contact contact = (Contact)bundle.getSerializable("contact");
        edtName.setText(contact.name);
        edtPhone.setText(contact.phone);
    }
}
