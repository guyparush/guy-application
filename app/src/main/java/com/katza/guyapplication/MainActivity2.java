package com.katza.guyapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
Button btnSave;
EditText etFname,etLname;
TextView tvDisplay;
SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        sp = getSharedPreferences("GuysSpfile", MODE_PRIVATE);

        String strFname = sp.getString("fname",null);
        String strLname = sp.getString("lname",null);
        if (strFname!=null && strLname!=null)
            tvDisplay.setText("welcome "+strFname+"   "+strLname);
    }
    private void initViews(){
        btnSave = findViewById(R.id.btnSubmit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = etFname.getText().toString();
                String lname = etLname.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("fname", fname);
                editor.putString("lname", lname);
                editor.commit(); // שומר
            }
        });



        etFname = findViewById(R.id.etFname);
    etLname = findViewById(R.id.etLname);
    tvDisplay = findViewById(R.id.tvDisplay);
    }
    }

