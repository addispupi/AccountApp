package com.gashadigital.exapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends Activity {

    EditText userRegFName, userRegLName, userRegEmail, userRegPass;
    String userF_name, userL_name, user_email, user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userRegFName = (EditText) findViewById(R.id.fname);
        userRegLName = (EditText) findViewById(R.id.lname);
        userRegEmail = (EditText) findViewById(R.id.userEmail);
        userRegPass = (EditText) findViewById(R.id.newPass);
    }
    public void uerReg(View view){
        userF_name = userRegFName.getText().toString();
        userL_name = userRegLName.getText().toString();
        user_email = userRegEmail.getText().toString();
        user_pass = userRegPass.getText().toString();
        String method = "Register";
        ServiceTask serviceTask = new ServiceTask(this);
        serviceTask.execute(method, userF_name, userL_name, user_email, user_pass);
        //finish();
    }
}