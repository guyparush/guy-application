package com.katza.guyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ActivitySecond extends AppCompatActivity {

    TextView tvName, tvAge, tvGender;
    EditText etBirthYear;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // אתחול של ה-TextViews ו-Button
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvGender = findViewById(R.id.tvGender);
        etBirthYear = findViewById(R.id.etBirthYear);
        btnConfirm = findViewById(R.id.btnConfirm);

        // קבל את הנתונים מה-Intent
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        int age = getIntent().getIntExtra("age", 0);
        boolean isMale = getIntent().getBooleanExtra("isMale", false);

        // הצגת הנתונים ב-TextView
        tvName.setText("שם: " + firstName + " " + lastName);
        tvAge.setText("גיל: " + age);
        tvGender.setText("זכר: " + (isMale ? "כן" : "לא"));

        // כפתור "אשר" - לחשב את גילך לפי שנת הלידה
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // קבל את שנת הלידה
                String birthYearStr = etBirthYear.getText().toString().trim();
                if (!birthYearStr.isEmpty()) {
                    int birthYear = Integer.parseInt(birthYearStr);
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int newAge = currentYear - birthYear;

                    // עדכן את הגיל ב-TextView
                    tvAge.setText("גיל: " + newAge);

                    // שלח את הגיל החדש חזרה ל-ActivityFirst
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newAge", newAge);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(ActivitySecond.this, "אנא הזן את שנת הלידה", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
