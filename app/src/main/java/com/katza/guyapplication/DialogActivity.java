package com.katza.guyapplication;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DialogActivity extends AppCompatActivity
        implements View.OnClickListener {

    SharedPreferences sp;
    Dialog d;
    EditText etUserName, etPass;
    Button btnCustomLogin, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dialog);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        sp = getSharedPreferences("details1", 0);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main));

