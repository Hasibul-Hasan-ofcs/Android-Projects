package com.example.polygon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.airbnb.lottie.LottieAnimationView;

public class Welcome_Activity extends AppCompatActivity {

    LottieAnimationView lottieThunder, lottieMainLogo;
    TextView mainLogoText;
    Typeface mainLogoTextTypeface;
    Animation innerThunderSplashAnimator;
    VideoView videoview;
    Button skipButton, nextButton, getStartedButton;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Window window = getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.deep_blue_02));

        //delay between window transition
        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(800);
        getWindow().setSharedElementEnterTransition(bounds);


        //select_font
        mainLogoTextTypeface = Typeface.createFromAsset(getAssets(),"font/DroidLogo_Bold.ttf");

        //find by id
        lottieThunder = findViewById(R.id.lottieThunderBoltSplash_02);
        lottieMainLogo = findViewById(R.id.lottiePolygonMainLogo);
        mainLogoText = findViewById(R.id.mainLogoName);
        skipButton = findViewById(R.id.skipButtonId);
        nextButton = findViewById(R.id.nextButtonId);
        getStartedButton = findViewById(R.id.getStartedButton);
//        videoview = findViewById(R.id.videoBackgroundView);

        //animation
        innerThunderSplashAnimator = AnimationUtils.loadAnimation(this,R.anim.outer_polygon_animation);


        //set_font
        mainLogoText.setTypeface(mainLogoTextTypeface);
        skipButton.setTypeface(mainLogoTextTypeface);
        nextButton.setTypeface(mainLogoTextTypeface);

        try {

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragment = new welcome_dive_deeper_fragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.fragmentWelcomeID,fragment);
                    fragmentTransaction.commit();

                            nextButton.setVisibility(View.GONE);
                            skipButton.setVisibility(View.GONE);
                            getStartedButton.setVisibility(View.VISIBLE);

                }
            });

            getStartedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferencesIntro = getSharedPreferences("intro", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editPopUpTheme = sharedPreferencesIntro.edit();
                    editPopUpTheme.putString("visibleNo", "one");
                    editPopUpTheme.commit();
                    skipButton.setTextColor(getResources().getColor(R.color.orange_01));
                    Intent intent = new Intent(Welcome_Activity.this, Pre_Browser_Main.class);
                    startActivity(intent);
                    finish();
                }
            });


            //play background video
//            videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.setLooping(true);
//                    PlaybackParams myPlayBackParams;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//                        myPlayBackParams = new PlaybackParams();
//                        myPlayBackParams.setSpeed(0.4f); //speed
//                        mp.setPlaybackParams(myPlayBackParams);
//                    }
//                }
//            });
//
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.polygon_background_black);
//            videoview.setVideoURI(uri);
//            videoview.start();


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


            skipButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sharedPreferencesIntro = getSharedPreferences("intro", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editPopUpTheme = sharedPreferencesIntro.edit();
                    editPopUpTheme.putString("visibleNo", "one");
                    editPopUpTheme.commit();
                    skipButton.setTextColor(getResources().getColor(R.color.orange_01));
                    Intent intent = new Intent(Welcome_Activity.this, Pre_Browser_Main.class);
                    startActivity(intent);
                    finish();
                }
            });
        }catch(Exception exception){
            Log.d("tag","error occurred.");
        }
    }
}