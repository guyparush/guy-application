package com.katza.guyapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView star, microphone;
    Button btn1, btn2, btn3, send;
    EditText guessnum;
    Switch sw;
    SeekBar sb;

    int randomNumber; // המספר הרנדומלי שיש לשער

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        generateRandomNumber();
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(10) + 1; // מספר בין 1 ל-10
    }

    private void initViews() {
        // כפתורים
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(v -> Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show());

        // תמונות וערכים
        star = findViewById(R.id.imageView);
        microphone = findViewById(R.id.microphone);
        guessnum = findViewById(R.id.guessnum);
        send = findViewById(R.id.send);

        // סוויץ' להפעלת מיקרופון
        sw = findViewById(R.id.sw);
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                microphone.setVisibility(View.VISIBLE);
            } else {
                microphone.setVisibility(View.INVISIBLE);
            }
        });

        // ************ SEEK BAR לבהירות הכוכב ************
        sb = findViewById(R.id.sb);
        sb.setMax(100);         // טווח מ-0 עד 100
        sb.setProgress(100);    // מתחיל בבהירות מלאה (100%)

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float alpha = progress / 100f; // מ-0 עד 1
                star.setAlpha(alpha);          // יותר ימינה – יותר בהיר
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // כפתור לשליחת ניחוש
        send.setOnClickListener(v -> {
            String input = guessnum.getText().toString();

            if (input.isEmpty()) {
                Toast.makeText(MainActivity.this, "אנא הזן מספר בין 1 ל-10", Toast.LENGTH_SHORT).show();
                return;
            }

            int userNumber = Integer.parseInt(input);

            if (userNumber < 1 || userNumber > 10) {
                Toast.makeText(MainActivity.this, "הזן מספר בין 1 ל-10", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userNumber == randomNumber) {
                Toast.makeText(MainActivity.this, "ניחוש נכון! המספר היה " + randomNumber, Toast.LENGTH_SHORT).show();
                generateRandomNumber(); // מתחילים משחק חדש
            } else {
                Toast.makeText(MainActivity.this, "לא נכון, נסה שוב!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            Toast.makeText(this, "Click other button", Toast.LENGTH_SHORT).show();
            star.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.btn2) {
            Toast.makeText(this, "Click other button", Toast.LENGTH_SHORT).show();
            star.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}

