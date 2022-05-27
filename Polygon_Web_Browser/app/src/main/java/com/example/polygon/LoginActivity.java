package com.example.polygon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    Button signUpFromHereButton;
    LottieAnimationView lottieThunder, lottieMainLogo;
    Animation innerThunderSplashAnimator02;
    Typeface mainLogoTextTypeface;
    TextInputLayout usernameInputLayout, passwordInputLayout;
    TextView mainLogoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            Window window = getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.deep_color_03));

            //find by id
            signUpFromHereButton = findViewById(R.id.signUpFromHereID);
            lottieThunder = findViewById(R.id.lottieThunderBoltSplash_02);
            lottieMainLogo = findViewById(R.id.lottiePolygonMainLogo);
            mainLogoText = findViewById(R.id.mainLogoName);
            usernameInputLayout = findViewById(R.id.usernameField);
            passwordInputLayout = findViewById(R.id.passwordField);


            //select_font
            mainLogoTextTypeface = Typeface.createFromAsset(getAssets(),"font/DroidLogo_Bold.ttf");

            //set_font
            mainLogoText.setTypeface(mainLogoTextTypeface);

            //animation
            innerThunderSplashAnimator02 = AnimationUtils.loadAnimation(this, R.anim.outer_polygon_animation);

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
                lottieMainLogo.setAnimation(innerThunderSplashAnimator02);

            }
        }, 6500);


            signUpFromHereButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                    Pair[] mkpair = new Pair[2];
//                    mkpair[0] = new Pair<View, String>(usernameInputLayout, "mainLogoNameTransition");
//                    mkpair[1] = new Pair<View, String>(passwordInputLayout, "thunderBoltName");
//
//                    ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, mkpair);
//                    startActivity(intent, op.toBundle());
                }
            });





        }catch(Exception exception){
            Log.d("tag","error occurred.");
        }

    }







}