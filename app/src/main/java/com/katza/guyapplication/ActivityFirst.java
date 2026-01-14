package com.katza.guyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityFirst extends AppCompatActivity {

    EditText etFname, etLname, etAge;
    CheckBox cbIsMale;
    Button btnSubmit, btnEditAge;

    // Launcher for ActivitySecond
    private ActivityResultLauncher<Intent> editAgeLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        etAge = findViewById(R.id.etAge);
        cbIsMale = findViewById(R.id.cbIsMale);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnEditAge = findViewById(R.id.btnEditAge);

        // Result Launcher
        editAgeLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            int newAge = result.getData().getIntExtra("age", 0);
                            etAge.setText(String.valueOf(newAge));
                        }
                    }
                });

        btnEditAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageStr = etAge.getText().toString().trim();
                int age = ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr);

                Intent intent = new Intent(ActivityFirst.this, ActivitySecond.class);
                intent.putExtra("age", age);
                editAgeLauncher.launch(intent);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // כאן אפשר לשמור או לשלוח את הנתונים
            }
        });
    }
}

