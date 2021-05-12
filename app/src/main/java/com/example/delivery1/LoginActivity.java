package com.example.delivery1;

import android.content.Intent;
import android.os.Bundle;

import com.example.delivery1.data.LocalDatabase;
import com.example.delivery1.data.UserEntity;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
                } else {
                    isValidManager = validate(inputName, inputPassword, "Manager");
                    if (isValidManager){
                        Toast.makeText(LoginActivity.this ,"Login Successful!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("Username",inputName);
                        startActivity(intent);
                    }
                    else{
                        isValidDriver = validate(inputName, inputPassword, "Driver");
                        if (isValidDriver){
                            Toast.makeText(LoginActivity.this ,"Login Successful!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, DriverSideActivity.class);
                            intent.putExtra("Username",inputName);
                            startActivity(intent);
                        }
                        else{
                            counter--;
                            Toast.makeText(LoginActivity.this ,"Incorrect! Please try again!",Toast.LENGTH_SHORT).show();
                            eAttemptsInfo.setText("Number of attempts remaining: "+counter);
                            if (counter==0){
                                eLogin.setEnabled(false);
                            }
                        }
                    }
                }

            }
        });

    }

    private boolean validate(String name, String password, String role) {
        // XXX: Hard-coded super user
        if (role.equals("Manager") && name.equals("Admin") && password.equals("123456"))
            return true;

        List<UserEntity> users = LocalDatabase.Companion.getDatabase(getApplicationContext()).userDao().getAllUserInfo();
        for (UserEntity u: users) {
            if (u.getUsername().equals(name) && u.getPassword().equals(password) && u.getRole().equals(role))
                return true;
        }
        return false;
    }

}