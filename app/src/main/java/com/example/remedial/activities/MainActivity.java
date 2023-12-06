package com.example.remedial.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remedial.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.buttonLogin = (Button) findViewById(R.id.login_btn);
        this.buttonLogin.setOnClickListener((evt) -> verifyCredentials());
    }
    protected void verifyCredentials(){
        EditText email =(EditText) findViewById(R.id.textEmail);
        EditText password = (EditText) findViewById(R.id.textPassword);
        if(email.getText().toString().equals("arturo@email.com") && password.getText().toString().equals("123456")){
            Intent intent = new Intent(this,UsersActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Credeciales incorrectas",Toast.LENGTH_LONG).show();
        }
    }
}