package com.katza.guyapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CostumActivity extends AppCompatActivity {

    SharedPreferences sp;
    Button btnSave;
    EditText etFname, etLname;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_costum);


        initViews();

        sp = getSharedPreferences("GuysSPFile", MODE_PRIVATE);
        String strFname = sp.getString("fname", null);
        String strLname = sp.getString("lname", null);

        if (strFname != null && strLname != null)
            tvDisplay.setText("welcome " + strFname + " " + strLname);

        // רישום ה-TextView ל-Context Menu
        registerForContextMenu(tvDisplay);
    }

    private void initViews() {
        btnSave = findViewById(R.id.btnSubmit);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("fname", etFname.getText().toString());
                editor.putString("lname", etLname.getText().toString());
                editor.commit();
            }
        });

        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        tvDisplay = findViewById(R.id.tvDisplay);
    }

    // ===== Options Menu (menu_main) =====
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_login) {
            Toast.makeText(this, "You selected login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, DialogActivity.class));
            finish();

        } else if (id == R.id.action_register) {
            Toast.makeText(this, "You selected register", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, DynamicActivity.class));
            finish();

        } else if (id == R.id.action_start) {
            Toast.makeText(this, "You selected start", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, CostumActivity.class));
            finish();
        }
        return true;
    }

    // ===== Context Menu (context_menu) =====
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.firstline) {
            Toast.makeText(this, "You selected first line", Toast.LENGTH_LONG).show();
            return true;

        } else if (item.getItemId() == R.id.secondline) {
            Toast.makeText(this, "You selected second line", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
