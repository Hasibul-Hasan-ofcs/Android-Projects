package com.example.polygon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView lottieThunder, lottieMainLogo;
    Animation innerThunderSplashAnimator;
    TextView mainLogoText, diveText, preparedByText;
    Typeface mainLogoTextTypeface, subLogoTextTypeface;
//    ConstraintLayout layout01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //select_font
        mainLogoTextTypeface = Typeface.createFromAsset(getAssets(),"font/DroidLogo_Bold.ttf");
        subLogoTextTypeface = Typeface.createFromAsset(getAssets(),"font/DroidLogo_Regular.ttf");

        //find by id
        lottieThunder = findViewById(R.id.lottieThunderBoltSplash_02);
        lottieMainLogo = findViewById(R.id.lottiePolygonMainLogo);
        mainLogoText = findViewById(R.id.mainLogoName);
//        preparedByText = findViewById(R.id.preparedBy);
        diveText = findViewById(R.id.diveNote);
//        layout01 =findViewById(R.id.logoHolderLayout);

        //animation
        innerThunderSplashAnimator = AnimationUtils.loadAnimation(this,R.anim.outer_polygon_animation);


        //set_font
        mainLogoText.setTypeface(mainLogoTextTypeface);


        try {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    lottieThunder.setAnimation(R.raw.thunder_03_green);
                    lottieMainLogo.setAnimation(innerThunderSplashAnimator);

                    blink_animation_function(diveText, 150, 1, "#FFFFFF", 0);

                }
            }, 6500);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences sharedPreferencesIntroAfter = getSharedPreferences("intro", Context.MODE_PRIVATE);
                    if(sharedPreferencesIntroAfter.contains("visibleNo")) {
                        String themeValue = sharedPreferencesIntroAfter.getString("visibleNo", "nothing found");
                        if (themeValue.contains("one")) {
                            Intent intent = new Intent(MainActivity.this,Pre_Browser_Main.class);
                            startActivity(intent);
                        }
                    }else {


                        Intent intentSplashView = new Intent(MainActivity.this, Welcome_Activity.class);
                        Pair[] mkpair = new Pair[1];
                        mkpair[0] = new Pair<View, String>(mainLogoText, "mainLogoNameTransition");

                        ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, mkpair);
                        startActivity(intentSplashView, op.toBundle());
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
            }, 8000);

        }catch(Exception e){
            Log.d("tag","Error occurred");
        }
    }

    private void blink_animation_function(TextView textView, int duration, int repeatCount, String color, int startOffSet){
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(duration);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(repeatCount);
                textView.startAnimation(anim);
                textView.setTextColor(Color.parseColor(color));

            }
        }, startOffSet);

    }
}