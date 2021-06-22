package com.gashadigital.exapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickLogin(View view){
        EditText emailText = (EditText) findViewById(R.id.email);
        EditText passText = (EditText) findViewById(R.id.pass);
        String userEmail = "addis@haile.com";
        String userPass = "new";


        if(emailText.getText().toString().equals(userEmail) && passText.getText().toString().equals(userPass)){
            Log.i("Info: ", "Successful");
            Log.i("email: ", emailText.getText().toString());
            Log.i("pass: ",passText.getText().toString());
        }
        else {
            Toast.makeText(this, "Username or Password not Correct", Toast.LENGTH_LONG).show();
        }

    }

    public void userRegister(View view) {
        startActivity(new Intent(this, Register.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}