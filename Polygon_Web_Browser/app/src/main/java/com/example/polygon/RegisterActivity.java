package com.example.polygon;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    LottieAnimationView lottieThunder, lottieMainLogo;
    Animation innerThunderSplashAnimator;
    Typeface mainLogoTextTypeface;
//    TextInputLayout usernameInputLayout, passwordInputLayout;
    TextView mainLogoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Window window = getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.deep_color_03));

        //find by id
        lottieThunder = findViewById(R.id.lottieThunderBoltSplash_02);
        lottieMainLogo = findViewById(R.id.lottiePolygonMainLogo);
        mainLogoText = findViewById(R.id.mainLogoName);

        //select_font
        mainLogoTextTypeface = Typeface.createFromAsset(getAssets(),"font/DroidLogo_Bold.ttf");

        //set_font
        mainLogoText.setTypeface(mainLogoTextTypeface);

        //animation
        innerThunderSplashAnimator = AnimationUtils.loadAnimation(this,R.anim.outer_polygon_animation);

        //setAnimation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieThunder.setAnimation(R.raw.thunder_03_green);

            }
        }, 6500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieMainLogo.setAnimation(innerThunderSplashAnimator);

            }
        }, 6500);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}