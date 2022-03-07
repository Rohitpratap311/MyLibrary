package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.passwordEditText);
        login = (Button) findViewById(R.id.btnLogin);


          login.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(email.getText().toString().equalsIgnoreCase("example@gmail.com")&&password.getText().toString().equals("example"))
                  {
                      Intent i=new Intent(LoginActivity.this,MainActivity.class);
                      startActivity(i);
                  }
                  else
                  Toast.makeText(LoginActivity.this, "Reneter Credentials", Toast.LENGTH_SHORT).show();
              }
          });

    }
}