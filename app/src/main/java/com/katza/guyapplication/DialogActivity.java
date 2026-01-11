package com.katza.guyapplication;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

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

        sp = getSharedPreferences("details1", MODE_PRIVATE);
    }

    public void createLoginDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.custom_layout);
        d.setTitle("login");
        d.setCancelable(true);

        etUserName = d.findViewById(R.id.etUserName);
        etPass = d.findViewById(R.id.etPassword);
        btnCustomLogin = d.findViewById(R.id.btnDialogLogin);

        btnCustomLogin.setOnClickListener(this);

        // טעינת נתונים שמורים (אם קיימים)
        String strname = sp.getString("username", null);
        String strpass = sp.getString("pass", null);

        if (strname != null && strpass != null) {
            etUserName.setText(strname);
            etPass.setText(strpass);
        }

        d.show();
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            createLoginDialog();
        } else if (v == btnCustomLogin) {

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", etUserName.getText().toString());
            editor.putString("pass", etPass.getText().toString());
            editor.apply();

            Toast.makeText(this,
                    "username password saved",
                    Toast.LENGTH_LONG).show();

            d.dismiss();
        }
    }
}

String strname = sp.getString("username",null);
String strpass = sp.getString("pass",null);
if(strname!=null&&strpass!=null) {
    etUserName.setText(strname);
    etPass.setText(strpass);
        }
@Override
public void onCliclk(View v)
{
    if(v==btnLogin) {
        createLoginDialog();
    } else if (v==btnCustomLogin) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",etUserName.getText().toString());
        editor.putString("pass",etPass.getText().toStirng());
        editor.commit();
        Toast.makeText(this,"username password saved",toast.LENGTH_LONG).show();

    }
}

