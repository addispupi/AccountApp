package com.gashadigital.exapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText emailText;
    private EditText passText;
    private TextView attemptInfo;
    private Button btnLogin;

    private String userEmail = "addis";
    private String userPass = "new";
    private String userName = "Addis";

    boolean isValid = false;
    private int passCounter = 5;


    public void userRegister(View view) {
        startActivity(new Intent(this, Register.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       emailText = findViewById(R.id.email);
       passText = findViewById(R.id.pass);
       attemptInfo = findViewById(R.id.tvAttemptInfo);
       btnLogin = findViewById(R.id.btnLogin);

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String getUser = emailText.getText().toString();
               String getPass = passText.getText().toString();
               String getAttempt = getString(R.string.attempt);


               if(getUser.equals("") || getPass.equals("")){
                   Toast.makeText(MainActivity.this, "Please provide info. All fields must be filled", Toast.LENGTH_SHORT).show();
               }
               else {
                   isValid = validate(getUser, getPass);
                   if(!isValid){
                       passCounter--;
                       Toast.makeText(MainActivity.this, "Username or Password not Correct !", Toast.LENGTH_SHORT).show();
                       attemptInfo.setText(getAttempt + " " +passCounter);
                       attemptInfo.setVisibility(View.VISIBLE);
                       if(passCounter <= 0){
                           btnLogin.setEnabled(false);
                           Log.i("Info: ","Disable the Button");
                       }
                   }
                   else{
                       Log.i("Info", "Successful");
                       Log.i("email", getUser);
                       Log.i("pass",getPass);
                       Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(MainActivity.this, HomePage.class));
                   }
               }
           }
       });

    }

    private boolean validate(String name, String password){
        if(name.equals(userEmail) && password.equals(userPass)){
            return true;
        }
        return false;
    }
}