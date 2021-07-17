package com.gashadigital.exapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText emailText;
    private EditText passText;
    private TextView attemptInfo;
    private Button btnLogin;
    Spinner spinner;

    final private String userEmail = "addis";
    final private String userPass = "new";

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
       spinner = findViewById(R.id.lanSpinner);

       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String lang = parent.getItemAtPosition(position).toString();
               String languageToLoad = null;

               if(lang.equals("አማርኛ")){
                   languageToLoad="am-rET";
                   Log.i("Info",languageToLoad);
               }
               else if(lang.equals("English")){
                   languageToLoad="en";
                   Log.i("Info",languageToLoad);
               }

               if(languageToLoad!=null){
                   Locale locale = new Locale(languageToLoad);
                   Locale.setDefault(locale);
                   Configuration config = getBaseContext().getResources().getConfiguration();
                   config.locale = locale;
                   getBaseContext().getResources().updateConfiguration(config,
                           getBaseContext().getResources().getDisplayMetrics());
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String getUser = emailText.getText().toString();
               String getPass = passText.getText().toString();
               String getAttempt = getString(R.string.attempt);

//               String method = "Login";
//               ServiceTask serviceTask = new ServiceTask(MainActivity.this);
//               serviceTask.execute(method, getUser, getPass);

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