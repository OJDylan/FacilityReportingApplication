package com.example.reportingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText username, password, cPassword;
    Button btnRegisterUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new DatabaseHelper(this);

        username = findViewById(R.id.register_username);
        password = findViewById(R.id.register_password);
        cPassword = findViewById(R.id.register_cpassword);
        btnRegisterUser = findViewById(R.id.register_user);
        AddUser();
    }

    public void AddUser(){
        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.addData(username.getText().toString(),
                        password.getText().toString());
                if(isInserted){
                    Toast.makeText(RegisterActivity.this,
                            "Successfully registered!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}