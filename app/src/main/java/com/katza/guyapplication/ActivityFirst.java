package com.katza.guyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityFirst extends AppCompatActivity {

    EditText etFname, etLname, etAge;
    CheckBox cbIsMale;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // אתחול כל המשתנים
        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        etAge = findViewById(R.id.EtAge); // ודא שה-ID תואם
        cbIsMale = findViewById(R.id.CbIsMale); // ודא שה-ID תואם
        btnSubmit = findViewById(R.id.btnSubmit);

        // כפתור שליחה (Submit)
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // קח את הנתונים מהשדות
                String firstName = etFname.getText().toString().trim();
                String lastName = etLname.getText().toString().trim();
                int age = Integer.parseInt(etAge.getText().toString().trim());
                boolean isMale = cbIsMale.isChecked();

                // שלח את הנתונים ל-ActivitySecond
                Intent intent = new Intent(ActivityFirst.this, ActivitySecond.class);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("age", age);
                intent.putExtra("isMale", isMale);
                startActivity(intent);
            }
        });
    }
}
