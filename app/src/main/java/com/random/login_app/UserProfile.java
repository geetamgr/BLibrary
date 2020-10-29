package com.random.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    TextView FullNameLabel, UsernameLabel;
    TextInputLayout profile_FullName, profile_email, profile_phoneNo, profile_password;
    DatabaseReference reference;
    Button ok;

    // Global Variables
    String _USERNAME, _NAME, _EMAIL, _PHONENO, _PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().hide();

        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Hooks
        FullNameLabel = findViewById(R.id.full_name);
        UsernameLabel = findViewById(R.id.user_name);
        profile_FullName = findViewById(R.id.profile_Fullname);
        profile_email = findViewById(R.id.profile_Email);
        profile_phoneNo = findViewById(R.id.profile_phoneNo);
        profile_password = findViewById(R.id.profile_password);
        ok = findViewById(R.id.ok);

        //Show All Data
        ShowAllData();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, Login.class));
            }
        });
    }

    private void ShowAllData() {
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        _NAME = intent.getStringExtra("name");
        _EMAIL = intent.getStringExtra("email");
        _PHONENO = intent.getStringExtra("phoneNo");
        _PASSWORD = intent.getStringExtra("password");

        FullNameLabel.setText(_NAME);
        UsernameLabel.setText(_USERNAME);
        profile_FullName.getEditText().setText(_NAME);
        profile_email.getEditText().setText(_EMAIL);
        profile_password.getEditText().setText(_PASSWORD);
        profile_phoneNo.getEditText().setText(_PHONENO);
    }
} //added later

   // public void update(View view) {   // view is used so that it can be called directly from design or xml file
      //  if (isNameChanged() || isPasswordChanged()) {
        //    Toast.makeText(this, "Data has been Updated.", Toast.LENGTH_SHORT).show();
        //} else {
         //   Toast.makeText(this, "Data is same and cannot be updated.", Toast.LENGTH_SHORT).show();
       // }
    //}

   // private boolean isNameChanged() {
     //   if (!_NAME.equals(profile_FullName.getEditText().getText().toString())) {  //getEditText is used because it is material design
        //    reference.child(_NAME).child("password").setValue(profile_FullName.getEditText().getText().toString());
          //  _NAME = profile_FullName.getEditText().getText().toString();
           // return true;
       // } else {
          //  return false;
       // }
  //  }


    //private boolean isPasswordChanged() {
      //  if (!_PASSWORD.equals(profile_password.getEditText().getText().toString())) {  //getEditText is used because it is material design
        //    reference.child(_PASSWORD).child("name").setValue(profile_password.getEditText().getText().toString());
          //  _PASSWORD = profile_password.getEditText().getText().toString();
          //  return true;
        //} else {
       //     return false;
     //   }
   // }
//}