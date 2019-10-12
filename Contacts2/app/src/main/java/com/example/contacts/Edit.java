package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    ImageButton mIbCancelEdit;
    ImageButton mIbDoneEdit;
    EditText edtName;
    EditText edtPhone;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mIbCancelEdit=findViewById(R.id.btn_CancelEdit);
        mIbDoneEdit=findViewById(R.id.btn_DoneEdit);
        edtName=findViewById(R.id.edt_Name);
        edtPhone=findViewById(R.id.edt_Phone);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("package");
        final Contact contact = (Contact)bundle.getSerializable("contact");
        edtName.setText(contact.name);
        edtPhone.setText(contact.phone);
        id = contact.getId();
        mIbDoneEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact newContact = new Contact();
                if ((edtName.getText().toString().equals("")) || (edtPhone.getText().toString().equals(""))) {
                    // hiển thị thông báo trên màn hình nếu người dùng chưa điền tên hoặc SĐT
                    Toast toast = Toast.makeText(Edit.this, "Không để trống tên hoặc số điện thoại", Toast.LENGTH_SHORT);
                    toast.show();
                    setResult(Activity.RESULT_CANCELED);
                } else {
                    newContact.setPhone(edtPhone.getText().toString());
                    newContact.setName(edtName.getText().toString());
                    newContact.setId(id);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("contact", newContact);

                    Intent intent = new Intent();
                    intent.putExtra("bundle", bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }

            }
        });
        mIbCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });


    }
}
