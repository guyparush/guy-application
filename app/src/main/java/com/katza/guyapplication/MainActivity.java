package com.katza.guyapplication;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
    TextView tv;

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

        tv = findViewById(R.id.tv);
        registerForContextMenu(tv);
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
        btn3.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show()
        );

        // תמונות ושדות
        star = findViewById(R.id.imageView);
        microphone = findViewById(R.id.microphone);
        guessnum = findViewById(R.id.guessnum);
        send = findViewById(R.id.send);

        // סוויץ' למיקרופון
        sw = findViewById(R.id.sw);
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            microphone.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
        });

        // SeekBar לבהירות הכוכב
        sb = findViewById(R.id.sb);
        sb.setMax(100);
        sb.setProgress(100);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float alpha = progress / 100f;
                star.setAlpha(alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // כפתור שליחת ניחוש
        send.setOnClickListener(v -> {
            String input = guessnum.getText().toString();

            if (input.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        "אנא הזן מספר בין 1 ל-10",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int userNumber = Integer.parseInt(input);

            if (userNumber < 1 || userNumber > 10) {
                Toast.makeText(MainActivity.this,
                        "הזן מספר בין 1 ל-10",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (userNumber == randomNumber) {
                Toast.makeText(MainActivity.this,
                        "ניחוש נכון! המספר היה " + randomNumber,
                        Toast.LENGTH_SHORT).show();
                generateRandomNumber();
            } else {
                Toast.makeText(MainActivity.this,
                        "לא נכון, נסה שוב!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // תפריט עליון
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_login) {
            Toast.makeText(this, "you selected login", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_register) {
            Toast.makeText(this, "you selected register", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_start) {
            Toast.makeText(this, "you selected start", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // בודק את פריטי הקונטקסט-מניו
        if (item.getItemId() == R.id.firstline) {
            Toast.makeText(this, "you selected first line", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.secondline) {
            Toast.makeText(this, "you selected second line", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
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

