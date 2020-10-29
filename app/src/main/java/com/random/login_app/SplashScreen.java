package com.random.login_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    //create a variable to make logo dynamic means show more than 1second
    private int SLEEP_TIMER = 3;
    ImageView image;
    TextView logo, ver;
    Animation topAnim, bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to get full screen move which remove all battery time tab of your mobile screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();  //to hide bar

        // Animation code
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animaiton);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = (ImageView)findViewById(R.id.imageView3) ;
        logo = (TextView)findViewById(R.id.textView);
        ver = (TextView)findViewById(R.id.textView2);

        image.setAnimation(bottomAnim);
        logo.setAnimation(topAnim);
        ver.setAnimation(topAnim);

        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }
    private  class LogoLauncher extends Thread{
        public void run(){
            try {
                sleep(1000 * SLEEP_TIMER);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            startActivity(new Intent(SplashScreen.this,Login.class));
            SplashScreen.this.finish(); //will destroy if button back is clicked
        }
    }
}


