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

public class AddContact extends AppCompatActivity {
    ImageButton mIbCancelAdd;
    ImageButton mIbDoneAdd;
    EditText mEdtName;
    EditText mEdtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        mIbCancelAdd=findViewById(R.id.btn_CancelAdd);
        mIbDoneAdd=findViewById(R.id.btn_DoneAdd);
        mEdtName=findViewById(R.id.add_Name);
        mEdtPhone=findViewById(R.id.add_Phone);
        mIbCancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED,intent);
                finish();
            }
        });
        mIbDoneAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact newContact = new Contact();
                if ((mEdtName.getText().toString().equals("")) || (mEdtPhone.getText().toString().equals(""))) {
                    // hiển thị thông báo trên màn hình nếu người dùng chưa điền tên hoặc SĐT
                    Toast toast = Toast.makeText(AddContact.this, "Không để trống tên hoặc số điện thoại", Toast.LENGTH_SHORT);
                    toast.show();
                    setResult(Activity.RESULT_CANCELED);
                } else {
                    newContact.setPhone(mEdtPhone.getText().toString());
                    newContact.setName(mEdtName.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("contact", newContact);

                    Intent intent = new Intent();
                    intent.putExtra("bundle", bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
