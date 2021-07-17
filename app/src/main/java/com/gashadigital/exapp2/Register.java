package com.gashadigital.exapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

    EditText userRegFName, userRegLName, userRegEmail, userRegPass, userRegConfirm;
    String userF_name, userL_name, user_email, user_pass, user_confirm;
    Button btnRegister;
    private boolean isValidate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userRegFName = (EditText) findViewById(R.id.fname);
        userRegLName = (EditText) findViewById(R.id.lname);
        userRegEmail = (EditText) findViewById(R.id.userEmail);
        userRegPass = (EditText) findViewById(R.id.newPass);
        userRegConfirm = findViewById(R.id.confirmPass);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userF_name = userRegFName.getText().toString();
                userL_name = userRegLName.getText().toString();
                user_email = userRegEmail.getText().toString();
                user_pass = userRegPass.getText().toString();
                user_confirm = userRegConfirm.getText().toString();

                isValidate = validate(userF_name, userL_name, user_email, user_pass);

                if(isValidate) {
                    String method = "Register";
                    ServiceTask serviceTask = new ServiceTask(Register.this);
                    serviceTask.execute(method, userF_name, userL_name, user_email, user_pass);

                }
            }
        });

    }

    private boolean validate(String rFName, String rLName, String rEmail, String rPass){
        if(rFName.equals("") || rLName.equals("") || rEmail.equals("") || rPass.equals("")){
            Toast.makeText(this, "All the fields can not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!rPass.equals(user_confirm)){
            Toast.makeText(this, "Both password fields should be the same", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}