package com.info.brochureatmobile.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.info.brochureatmobile.MainActivity;
import com.info.brochureatmobile.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_tIME_OUT = 3000;
    ImageView Splash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        init();
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash_activity);

           /* Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fad);
            Splash.startAnimation(animation);*/

            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {

                    SharedPreferences sh
                            = getSharedPreferences("Subscription",
                            MODE_PRIVATE);
                    String status = sh.getString("status", "");
                    if (status.equalsIgnoreCase("1")) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, SubscriptionActivity.class);
                        startActivity(intent);
                        finish();
                    }


                }

            }, SPLASH_tIME_OUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void init() {
        Splash = findViewById(R.id.Splash);
    }
}
