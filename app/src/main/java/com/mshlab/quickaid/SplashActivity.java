package com.mshlab.quickaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mshlab.quickaid.R;
import com.mshlab.quickaid.Uitls.App;
import com.mshlab.quickaid.Uitls.TinyDB;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        App.init(this);

        boolean introDone = new TinyDB(App.mContext).getBoolean("userDoneIntro");

        if (!introDone) {
            Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }


    }


}
