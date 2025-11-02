package com.katza.guyapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView star;
    Button btn1, btn2, btn3;
    Button send;
    EditText guessnum;

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

        // הפעם נגריל את המספר רק פעם אחת בהתחלה
        generateRandomNumber();
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(10) + 1; // מספר בין 1 ל-10
    }

    private void initViews() {
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show()
        );

        star = findViewById(R.id.imageView);
        guessnum = findViewById(R.id.guessnum);
        send = findViewById(R.id.send);

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
        } else if (v == btn2) {
            Toast.makeText(this, "click other button", Toast.LENGTH_SHORT).show();
            star.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
    }
}
