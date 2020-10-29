package com.random.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;

    //for firebase
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        //Hooks to all xml elements
        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id.phoneNo);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);

        //Save date in FireBase on button click
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");
                if (!validateName() | !validateEmail() | !validateUsername() | !validatePhoneNo() | !validatePassword()) {
                    return; //if not written "return" then data is not stored or will delete the presence data in database
                }
                // Get all the values
               String name = regName.getEditText().getText().toString();
               String username = regUsername.getEditText().getText().toString();
               String email = regEmail.getEditText().getText().toString();
               String phoneNo = regPhoneNo.getEditText().getText().toString();
               String password = regPassword.getEditText().getText().toString();

                //call our class
                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
                reference.child(username).setValue(helperClass);
            }



        });//Register button method end
        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });
        //to validate each field

    } //onCreate methods end

        private Boolean validateName () {
            String val = regName.getEditText().getText().toString();
            if (val.isEmpty()) {
                regName.setError("Field cannot be empty");
                return false;  //setError because it is material inputLayout
            } else {
                regName.setError(null);  //hide the error
                regName.setErrorEnabled(false);  //remove the space
                return true;
            }
        }

        private Boolean validateUsername () {
            String val = regUsername.getEditText().getText().toString();
            String noWhiteSpace = "\\A\\w{4,20}\\z"; // or   "(?=\\s+$)"
            if (val.isEmpty()) {
                regUsername.setError("Field cannot be empty");
                return false;  //setError because it is material inputLayout
            } else if (val.length() >= 15) {
                regUsername.setError("Username too long");
                return false;
            } else if (!val.matches(noWhiteSpace)) {
                regUsername.setError("More than four char."); // or Whitespace are not allowed
                return false;
            } else {
                regUsername.setError(null);
                return true;
            }
        }

        private Boolean validateEmail () {
            String val = regEmail.getEditText().getText().toString();
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if (val.isEmpty()) {
                regEmail.setError("Field cannot be empty");
                return false;  //setError because it is material inputLayout
            } else if (!val.matches(emailPattern)) {
                regEmail.setError("Invalid Email Address");
                return false;
            } else {
                regEmail.setError(null);
                return true;
            }
        }

        private Boolean validatePhoneNo () {
            String val = regPhoneNo.getEditText().getText().toString();
            if (val.isEmpty()) {
                regPhoneNo.setError("Field cannot be empty");
                return false;  //setError because it is material inputLayout
            } else {
                regPhoneNo.setError(null);
                return true;
            }
        }

        private Boolean validatePassword () {
            String val = regPassword.getEditText().getText().toString();
            String passwordVal = "^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$";
            if (val.isEmpty()) {
                regPassword.setError("Field cannot be empty");
                return false;  //setError because it is material inputLayout
            } else if (!val.matches(passwordVal)) {
                regPassword.setError("Password is to weak");
                return false;
            } else {
                regPassword.setError(null);
                return true;
            }
        }
}