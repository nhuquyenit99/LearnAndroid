package com.example.calculationv2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtA, mEdtB;
    private TextView mTvResult;
    private Button mBtnAdd, mBtnSub, mBtnMul, mBtnDiv;
    private Toast mNullAlert;

    private ListView lvTest;
    private ListView mLvResult;
    ArrayList<String> mListResult;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtA = findViewById(R.id.edt_A);
        mEdtB = findViewById(R.id.edt_B);
        mTvResult = findViewById(R.id.tvResult);

        mListResult = new ArrayList<String>();

        mLvResult = findViewById(R.id.lv_result);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                mListResult);

        Log.d("debug1", "" +   mLvResult );
        mLvResult.setAdapter(arrayAdapter);


/*
    (2) Anonymous listener
 */
        mBtnSub = findViewById(R.id.btn_Sub);
        mBtnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double a = getDoubleFromText(mEdtA);
                    double b = getDoubleFromText(mEdtB);
                    String stringResult = a + " - " + b + " = " + (a - b);
                    mTvResult.setText(stringResult);
                    mListResult.add(0,stringResult);
                    arrayAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    mNullAlert = Toast.makeText(MainActivity.this,
                            "Không được để trống!",Toast.LENGTH_SHORT);
                    mNullAlert.show();
                }
            }
        });
/*
    (4) Activity is listener
*/
        mBtnMul = findViewById(R.id.btn_Mul);
        mBtnMul.setOnClickListener(this);

/*
    (2) Anonymous listener
 */
        mBtnDiv = findViewById(R.id.btn_Div);
        mBtnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double a = getDoubleFromText(mEdtA);
                    double b = getDoubleFromText(mEdtB);
                    String stringResult;
                    if (b == 0) {
                        stringResult = "Lỗi chia cho 0!";
                    } else {
                        stringResult = a + " / " + b + " = " +
                                (double) Math.round(a / b * 1000) / 1000;
                    }
                    mTvResult.setText(stringResult);
                    mListResult.add(0,stringResult);
                } catch (Exception e) {
                    mNullAlert = Toast.makeText(MainActivity.this,
                            "Không được để trống!",Toast.LENGTH_SHORT);
                    mNullAlert.show();
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }

    /*
        (1) Onclick in XML
    */
    public void doAdd(View v) {
        try {
            double a = getDoubleFromText(mEdtA);
            double b = getDoubleFromText(mEdtB);
            String stringResult = a + " + " + b + " = " + (a + b);
            mTvResult.setText(stringResult);
            mListResult.add(0,stringResult);
        } catch (Exception e) {
            mNullAlert = Toast.makeText(MainActivity.this,"Không được để trống!",
                    Toast.LENGTH_SHORT);
            mNullAlert.show();
        }
        arrayAdapter.notifyDataSetChanged();
    }

    /*
    Fuction get number from Edit text to double
     */
    public double getDoubleFromText(EditText mEdt) throws RuntimeException {
        if (mEdt.getText().toString() == "") {
            throw new RuntimeException("error");
        } else {
            return Double.parseDouble(mEdt.getText().toString());
        }
    }
    /*
    For Activity is Listener
     */
    @Override
    public void onClick(View view) {
        try {
            double a = getDoubleFromText(mEdtA);
            double b = getDoubleFromText(mEdtB);
            String stringResult = a + " * " + b + " = " + (a * b);
            mTvResult.setText(stringResult);
            mListResult.add(0,stringResult);
        } catch (Exception e) {
            mNullAlert = Toast.makeText(MainActivity.this,"Không được để trống!",
                    Toast.LENGTH_SHORT);
            mNullAlert.show();
        }
        arrayAdapter.notifyDataSetChanged();
    }
}

