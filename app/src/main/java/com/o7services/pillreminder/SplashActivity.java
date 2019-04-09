package com.o7services.pillreminder;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = findViewById(R.id.splash_logo);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.logo_transition);
        imageView.startAnimation(animation);

        final Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        final ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(SplashActivity.this, android.R.anim.fade_in, android.R.anim.fade_out);

        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        startActivity(intent, activityOptions.toBundle());
                        finish();
                    }

                }
            }).start();
        }
        catch (IllegalArgumentException e){
            startActivity(intent, activityOptions.toBundle());
            finish();
        }

    }
}
