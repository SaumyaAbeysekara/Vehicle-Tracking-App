package com.example.fbasevedio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    EditText username,password;
    Button loginBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("saumya") && password.getText().toString().equals("1234")) {
                    Intent intent = new Intent(loginActivity.this, HomePage.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(loginActivity.this, "incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}