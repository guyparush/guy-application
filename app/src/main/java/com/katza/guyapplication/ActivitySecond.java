package com.katza.guyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ActivitySecond extends AppCompatActivity {

    EditText etBirthYear;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etBirthYear = findViewById(R.id.etBirthYear);
        btnConfirm = findViewById(R.id.btnConfirm);

        // אם נשלח גיל קודם, הצג אותו כדי לחשב את שנת הלידה
        int currentAge = getIntent().getIntExtra("age", 0);
        if (currentAge > 0) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            etBirthYear.setText(String.valueOf(currentYear - currentAge));
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String birthYearStr = etBirthYear.getText().toString().trim();
                if (!birthYearStr.isEmpty()) {
                    int birthYear = Integer.parseInt(birthYearStr);
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int newAge = currentYear - birthYear;

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("age", newAge);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
