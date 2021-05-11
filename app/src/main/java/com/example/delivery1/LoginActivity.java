package com.example.delivery1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText eName;
    private TextInputEditText ePassword;
    private Button eLogin;
    private TextView eAttemptsInfo;

    private String Username = "Admin";
    private String Password = "123456";
    private String DriverUsername = "Sam";
    private String DriverPassword = "123456";
    private Boolean isValidManager = false;
    private Boolean isValidDriver =false;
    private int counter =5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eName = findViewById(R.id.username_text);
        ePassword = findViewById(R.id.password_text);
        eLogin = findViewById(R.id.login_button);
        eAttemptsInfo = findViewById(R.id.attempt_left);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();
                if(inputName.isEmpty()||inputPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this ,"Please enter details correctly!",Toast.LENGTH_SHORT).show();

                }
                else{
                    isValidManager = validatedManager(inputName,inputPassword);
                    if (!isValidManager){
                        counter--;
                        Toast.makeText(LoginActivity.this ,"Incorrect! Please try again!",Toast.LENGTH_SHORT).show();
                        eAttemptsInfo.setText("Number of attempts remaining: "+counter);
                        if (counter==0){
                            eLogin.setEnabled(false);
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this ,"Login Successful!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    isValidDriver = validatedDriver(inputName,inputPassword);
                    if (!isValidDriver){
                        counter--;
                        Toast.makeText(LoginActivity.this ,"Incorrect! Please try again!",Toast.LENGTH_SHORT).show();
                        eAttemptsInfo.setText("Number of attempts remaining: "+counter);
                        if (counter==0){
                            eLogin.setEnabled(false);
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this ,"Login Successful!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,DriverSide.class);
                        startActivity(intent);
                    }
                }

            }
        });

    }

    private boolean validatedManager(String name, String password){
        if(name.equals(Username) && password.equals(Password)){
            return true;
        }
        return false;
    }

    private boolean validatedDriver(String name, String password){
        if(name.equals(DriverUsername) && password.equals(DriverPassword)){
            return true;
        }
        return false;
    }

}