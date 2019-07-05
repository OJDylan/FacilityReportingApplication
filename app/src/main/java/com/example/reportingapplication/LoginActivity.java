package com.example.reportingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button login, register;
    EditText mUsername, mPassword;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb = new DatabaseHelper(this);

        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);
        mUsername = findViewById(R.id.editText_username);
        mPassword = findViewById(R.id.editText_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUsername.getText().toString();
                String pass = mPassword.getText().toString();
                if (checkInput(user, pass)) {
                    boolean res = myDb.checkUser(user, pass);
                    if (res) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Text fields are empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public boolean checkInput(String user, String pass){
        return (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass));
    }
}
