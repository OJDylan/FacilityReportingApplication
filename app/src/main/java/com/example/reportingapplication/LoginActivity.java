package com.example.reportingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reportingapplication.Database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.reportingapplication.example.EXTRA_TEXT";
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
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(EXTRA_TEXT, user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Incorrect username or password", Toast.LENGTH_SHORT).show();
                        mUsername.setText("");
                        mPassword.setText("");
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

    //checks for empty input
    public boolean checkInput(String user, String pass){
        return (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass));
    }
}
