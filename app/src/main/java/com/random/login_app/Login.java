package com.random.login_app;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout libraryId, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This line will hide the status bar or we can say a blue screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //Hooks
        callSignUp = findViewById(R.id.signUp);
        login_btn = findViewById(R.id.login_btn);
        image = findViewById(R.id.logoImage);
        logoText = findViewById(R.id.logoName);
        sloganText = findViewById(R.id.signIn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        libraryId = findViewById(R.id.Library_ID);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                Pair[] pairs = new Pair[8];
                pairs[0] = new Pair<View, String>(image, "logo_image"); //green color text is animation name assigned in xml file
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(sloganText, "slogan");
                pairs[3] = new Pair<View, String>(username, "login_tran");
                pairs[4] = new Pair<View, String>(password, "login_tran");
                pairs[5] = new Pair<View, String>(login_btn, "button_tran");
                pairs[6] = new Pair<View, String>(callSignUp, "button_tran");
                pairs[7] = new Pair<View, String>(libraryId, "login_tran");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) { //this condition is default done in red bulb effect by api level in android itself
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent, options.toBundle()); //to Bundle carries the animations
                }

            }
        });

    }

    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword() | !validateLibraryId()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        final String UserEnteredUsername = username.getEditText().getText().toString().trim();
        final String UserEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(UserEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()) {
                   //if user exists when tried next time
                   username.setError(null);
                   username.setErrorEnabled(false);
                   String passwordFromDB = dataSnapshot.child(UserEnteredUsername).child("password").getValue(String.class);

                   if (passwordFromDB.equals(UserEnteredPassword)) {
                       // when user tried for second time and the data exists in DB
                       password.setError(null);
                       password.setErrorEnabled(false);

                       String emailFromDB = dataSnapshot.child(UserEnteredUsername).child("email").getValue(String.class);
                       String nameFromDB = dataSnapshot.child(UserEnteredUsername).child("name").getValue(String.class);
                       String phoneNoFromDB = dataSnapshot.child(UserEnteredUsername).child("phoneNo").getValue(String.class);
                       String usernameFromDB = dataSnapshot.child(UserEnteredUsername).child("username").getValue(String.class);

                       Intent intent = new Intent(getApplicationContext(), Menus.class);
                       intent.putExtra("email", emailFromDB);
                       intent.putExtra("name", nameFromDB); //put extra to get object from one activity to another activity
                       intent.putExtra("password", passwordFromDB);
                       intent.putExtra("phoneNo", phoneNoFromDB);
                       intent.putExtra("username", usernameFromDB);
                       startActivity(intent);
                   } else {
                       password.setError("Wrong Password");
                       password.requestFocus();
                   }
               } else {
                   username.setError("No such User Exists");
                   username.requestFocus();
               }
           }
           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) { }
       });

    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;  //setError because it is material inputLayout
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;  //setError because it is material inputLayout
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
    private  Boolean validateLibraryId(){
        String val = libraryId.getEditText().getText().toString();
        if (val.isEmpty()) {
            libraryId.setError("Field cannot be empty");
            return false;  //setError because it is material inputLayout
        } else {
            libraryId.setError(null);
            libraryId.setErrorEnabled(false);
            return true;
        }
    }
}



