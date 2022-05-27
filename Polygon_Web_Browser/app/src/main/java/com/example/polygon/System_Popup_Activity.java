package com.example.polygon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class System_Popup_Activity extends AppCompatActivity {

    ImageButton closePopupButton, changePopupThemeColor, navHome, navTab, navReload, powerButton;
    Button settingsPopupButton, bookmarksPopupButton, supportPopupButton, themesPopupButton, downloadPopupButton, incognitoPopupButton, historyPopupButton, sharePopupButton, aboutPopupButton;
    Animation buttonRotateAnimation;
    LottieAnimationView lottieSettings, lottieBookmarks, lottieVpn, lottieTheme, lottieDownload, lottieIncognito, lottieHistory, lottieShare, lottieAbout;
    LinearLayout settingsIconHolder, bookmarksIconHolder, vpnIconHolder, themesIconHolder, downloadIconHolder, incognitoIconHolder, historyIconHolder, shareIconHolder, aboutIconHolder, popupMainLayout, popupSecondaryLayout, dividerId01, dividerId02, dividerId03, dividerId04, dividerId05, dividerId06, navBottomHolder;
    TextView settingsPopupText, bookmarksPopupText, supportPopupText, themePopupText, downloadPopupText, incognitoPopupText, historyPopupText, sharePopupText, aboutPopupText;
    View dividerId10, dividerId11, dividerId12, dividerId13, dividerId14, dividerId15, dividerId20;
    private int themeChangeCounter=0;
    String message = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_popup);

//      set popup window(Resolution,position)

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        getWindow().setLayout((int)(width*0.9),(int)(height*0.8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_VERTICAL;

//      to make behind dim (solution)
        params.dimAmount=0.7f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

//      to make popup behind color transparent
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        params.x=0;
        params.y=-20;

        getWindow().setAttributes(params);

//      find by id
        navHome = findViewById(R.id.navHome);
        navTab = findViewById(R.id.navTab);
        navReload = findViewById(R.id.navReload);
        closePopupButton = findViewById(R.id.closePopupButton);
        lottieSettings = findViewById(R.id.settings);
        lottieBookmarks = findViewById(R.id.bookmarks);
        lottieVpn = findViewById(R.id.vpn);
        lottieTheme = findViewById(R.id.themes);
        lottieDownload = findViewById(R.id.download);
        lottieIncognito = findViewById(R.id.incognito);
        lottieHistory = findViewById(R.id.history);
        lottieShare = findViewById(R.id.share);
        lottieAbout = findViewById(R.id.about);
        changePopupThemeColor = findViewById(R.id.popupThemeChange);
        popupMainLayout = findViewById(R.id.popupMainLayout);
        popupSecondaryLayout = findViewById(R.id.popupSecondaryLayout);
        settingsPopupText = findViewById(R.id.settingsPopupText);
        bookmarksPopupText = findViewById(R.id.bookmarksPopupText);
        supportPopupText = findViewById(R.id.vpnPopupText);
        themePopupText = findViewById(R.id.themesPopupText);
        downloadPopupText = findViewById(R.id.downloadPopupText);
        incognitoPopupText = findViewById(R.id.incognitoPopupText);
        historyPopupText = findViewById(R.id.historyPopupText);
        sharePopupText = findViewById(R.id.sharePopupText);
        aboutPopupText = findViewById(R.id.aboutPopupText);
        settingsPopupButton = findViewById(R.id.settingsPopupButton);
        bookmarksPopupButton = findViewById(R.id.bookmarksPopupButton);
        supportPopupButton = findViewById(R.id.vpnPopupButton);
        themesPopupButton = findViewById(R.id.themesPopupButton);
        downloadPopupButton = findViewById(R.id.downloadPopupButton);
        incognitoPopupButton = findViewById(R.id.incognitoPopupButton);
        historyPopupButton = findViewById(R.id.historyPopupButton);
        sharePopupButton = findViewById(R.id.sharePopupButton);
        aboutPopupButton = findViewById(R.id.aboutPopupButton);
        settingsIconHolder = findViewById(R.id.settingsIconHolder);
        bookmarksIconHolder = findViewById(R.id.bookmarksHolder);
        vpnIconHolder = findViewById(R.id.vpnHolder);
        themesIconHolder = findViewById(R.id.themesIconHolder);
        downloadIconHolder = findViewById(R.id.downloadIconHolder);
        incognitoIconHolder = findViewById(R.id.incognitoIconHolder);
        historyIconHolder = findViewById(R.id.historyIconHolder);
        shareIconHolder = findViewById(R.id.shareIconHolder);
        aboutIconHolder = findViewById(R.id.aboutIconHolder);

//      divider id's
        dividerId01 = findViewById(R.id.dividerId01);
        dividerId02 = findViewById(R.id.dividerId02);
        dividerId03 = findViewById(R.id.dividerId03);
        dividerId04 = findViewById(R.id.dividerId04);
        dividerId05 = findViewById(R.id.dividerId05);
        dividerId06 = findViewById(R.id.dividerId06);
        dividerId10 = findViewById(R.id.dividerId10);
        dividerId11 = findViewById(R.id.dividerId11);
        dividerId12 = findViewById(R.id.dividerId12);
        dividerId13 = findViewById(R.id.dividerId13);
        dividerId14 = findViewById(R.id.dividerId14);
        dividerId15 = findViewById(R.id.dividerId15);
        dividerId20 = findViewById(R.id.dividerId20);

        navBottomHolder = findViewById(R.id.navBottomHolder);
        powerButton = findViewById(R.id.powerButton);

//      animation load
        buttonRotateAnimation = AnimationUtils.loadAnimation(System_Popup_Activity.this,R.anim.button_rotation_180degree_animation);

//      taking array
        LinearLayout[] dividerLinear = {dividerId01, dividerId02, dividerId03, dividerId04, dividerId05, dividerId06};
        View[] dividerView = {dividerId10, dividerId11, dividerId12, dividerId13, dividerId14, dividerId15, dividerId20};
        TextView[] arrayTextPopup = {settingsPopupText, bookmarksPopupText, supportPopupText, themePopupText, downloadPopupText, incognitoPopupText, historyPopupText, sharePopupText, aboutPopupText};
        LottieAnimationView[] arrayLottieAnimationSet = {lottieSettings, lottieBookmarks, lottieVpn, lottieTheme, lottieDownload, lottieIncognito, lottieHistory, lottieShare, lottieAbout};
        Button[] arrayButtonPopup = {settingsPopupButton, bookmarksPopupButton, supportPopupButton, themesPopupButton, downloadPopupButton, incognitoPopupButton, historyPopupButton, sharePopupButton, aboutPopupButton};

//      setting animation
        for(int i=0;i<9;i++){
            setAnimationLottie(arrayLottieAnimationSet[i]);
        }

//        setting all button to white
        for(int i=0;i<9;i++){
            setColor(arrayTextPopup[i],2);
        }

//      setting button on the lottie files
        for(int i=0;i<9;i++){
            setButtonClickListener(arrayButtonPopup[i]);
        }

//      setSharedPreferences (choice data store)
        SharedPreferences sharedPreferencesPopUpThemePreLoad = getSharedPreferences("PopupTheme", Context.MODE_PRIVATE);
        if(!sharedPreferencesPopUpThemePreLoad.contains("themeValue")) {
            String themeValue = sharedPreferencesPopUpThemePreLoad.getString("themeValue", "nothing found");
//            Toast.makeText(System_Popup_Activity.this, themeValue, Toast.LENGTH_SHORT).show();
            if(themeValue.contains("nothing found")){
                SharedPreferences sharedPreferencesPopUpTheme = getSharedPreferences("PopupTheme", Context.MODE_PRIVATE);
                SharedPreferences.Editor editPopUpTheme = sharedPreferencesPopUpTheme.edit();
                editPopUpTheme.putString("themeValue", "two");
                editPopUpTheme.commit();
            }
        }

        SharedPreferences sharedPreferencesPopUpTheme01 = getSharedPreferences("PopupTheme", Context.MODE_PRIVATE);
        if(sharedPreferencesPopUpTheme01.contains("themeValue")){
            String themeValue = sharedPreferencesPopUpTheme01.getString("themeValue","nothing found");
//            Toast.makeText(System_Popup_Activity.this, themeValue, Toast.LENGTH_SHORT).show();
            if(themeValue.contains("one")){
                popupSecondaryLayout.setBackgroundResource(R.drawable.round_background_popup_window_white_03);
                for (LinearLayout linearLayout : dividerLinear) {
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.deep_color_03));
                }
                for (View value : dividerView) {
                    value.setBackgroundColor(getResources().getColor(R.color.deep_color_03));
                }
                dividerId20.setBackgroundColor(getResources().getColor(R.color.deep_color_03));
                for(int i=0;i<9;i++){
                    setColor(arrayTextPopup[i],1);
                }
            }else if(themeValue.contains("two")){
                popupSecondaryLayout.setBackgroundResource(R.drawable.round_background_popup_window_black);
                for (LinearLayout linearLayout : dividerLinear) {
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
                }
                for (View value : dividerView) {
                    value.setBackgroundColor(getResources().getColor(R.color.white));
                }
                dividerId20.setBackgroundColor(getResources().getColor(R.color.white));
                for(int i=0;i<9;i++){
                    setColor(arrayTextPopup[i],2);
                }
            }
        }

        powerButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
//                   Intent powerIntent = new Intent(System_Popup_Activity.this,Pre_Browser_Main.class);
//                   powerIntent.putExtra("decision","exit");
//                   startActivity(powerIntent);
//                   finish();
                   System.exit(0);

               }
        });


//      event listeners
                navHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent homeIntent = new Intent(System_Popup_Activity.this, Pre_Browser_Main.class);
                        startActivity(homeIntent);
                        overridePendingTransition(R.anim.hold, R.anim.slide_in_right);
                        finish();
                    }
                });

//        navReload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


        closePopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Toast.makeText(System_Popup_Activity.this, "popup closed", Toast.LENGTH_SHORT).show();
            }
        });
        changePopupThemeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(System_Popup_Activity.this, "hi", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferencesPopUpTheme01 = getSharedPreferences("PopupTheme", Context.MODE_PRIVATE);
                if (sharedPreferencesPopUpTheme01.contains("themeValue")) {
                    String themeValue = sharedPreferencesPopUpTheme01.getString("themeValue", "Nothing found");
                    if(themeValue.contains("two")){
                    popupSecondaryLayout.setBackgroundResource(R.drawable.round_background_popup_window_white_03);

                    SharedPreferences sharedPreferencesPopUpTheme = getSharedPreferences("PopupTheme", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editPopUpTheme = sharedPreferencesPopUpTheme.edit();
                    editPopUpTheme.putString("themeValue", "one");
                    editPopUpTheme.commit();
                    for (LinearLayout linearLayout : dividerLinear) {
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.deep_color_03));
                    }
                    for (View value : dividerView) {
                        value.setBackgroundColor(getResources().getColor(R.color.deep_color_03));
                    }
                    dividerId20.setBackgroundColor(getResources().getColor(R.color.deep_color_03));

//                    popupThemeChangeCount = 1;
                    for (int i = 0; i < 9; i++) {
                        setColor(arrayTextPopup[i], 1);
                    }
                    changePopupThemeColor.startAnimation(buttonRotateAnimation);

                }else if(themeValue.contains("one")) {
                    popupSecondaryLayout.setBackgroundResource(R.drawable.round_background_popup_window_black);

                    SharedPreferences sharedPreferencesPopUpTheme = getSharedPreferences("PopupTheme", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editPopUpTheme = sharedPreferencesPopUpTheme.edit();
                    editPopUpTheme.putString("themeValue", "two");
                    editPopUpTheme.commit();
                    for (LinearLayout linearLayout : dividerLinear) {
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                    for (View value : dividerView) {
                        value.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                    dividerId20.setBackgroundColor(getResources().getColor(R.color.white));
                    for(int i = 0; i < 9; i++) {
                        setColor(arrayTextPopup[i], 2);
                    }
                    changePopupThemeColor.startAnimation(buttonRotateAnimation);
                }
              }
            }
        });

    }
    public void setButtonClickListener(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferencesPopUpTheme01 = getSharedPreferences("PopupTheme", Context.MODE_PRIVATE);
                if(sharedPreferencesPopUpTheme01.contains("themeValue")){
                    String themeValue = sharedPreferencesPopUpTheme01.getString("themeValue","Nothing found");
                    if(themeValue.contains("two")){

                        if (view.getId() == R.id.settingsPopupButton) {
                            settingsIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_white_02);
                            Intent intent = new Intent(System_Popup_Activity.this, Settings_Activity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_in_bottom, R.anim.hold);
                        } else if (view.getId() == R.id.bookmarksPopupButton) {
                            bookmarksIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_white_02);
                            Intent intent = new Intent(System_Popup_Activity.this, Bookmarks_Activity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_in_bottom, R.anim.hold);
                        } else if (view.getId() == R.id.vpnPopupButton) {
                            vpnIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_white_02);
                        } else if (view.getId() == R.id.themesPopupButton) {
                            themesIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_white_02);
                        } else if (view.getId() == R.id.downloadPopupButton) {
                            downloadIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_white_02);
                        } else if (view.getId() == R.id.incognitoPopupButton) {
                            incognitoIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_white_02);
                        } else if (view.getId() == R.id.historyPopupButton) {
                            historyIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_white_02);
                            Intent intent = new Intent(System_Popup_Activity.this, History_Activity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_in_left, R.anim.hold);
                        } else if (view.getId() == R.id.sharePopupButton) {
                            shareIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_white_02);
                        } else if (view.getId() == R.id.aboutPopupButton) {
//                            aboutIconHolder.setBackground(getResources().getDrawable(R.drawable.round_background_popup_window_white_02));
                            aboutIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_white_02);
                        }
                    }
                    else if (themeValue.contains("one")) {
                        if (view.getId() == R.id.settingsPopupButton) {
                            settingsIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_black);
                            Intent intent = new Intent(System_Popup_Activity.this, Settings_Activity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_in_bottom, R.anim.hold);
                        } else if (view.getId() == R.id.bookmarksPopupButton) {
                            bookmarksIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_black);
                            Intent intent = new Intent(System_Popup_Activity.this, Bookmarks_Activity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_in_bottom, R.anim.hold);
                        } else if (view.getId() == R.id.vpnPopupButton) {
                            vpnIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_black);
                        } else if (view.getId() == R.id.themesPopupButton) {
                            themesIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_black);
                        } else if (view.getId() == R.id.downloadPopupButton) {
                            downloadIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_black);
                        } else if (view.getId() == R.id.incognitoPopupButton) {
                            incognitoIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_black);
                        } else if (view.getId() == R.id.historyPopupButton) {
                            historyIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_black);
                            Intent intent = new Intent(System_Popup_Activity.this, History_Activity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_in_left, R.anim.hold);
                        } else if (view.getId() == R.id.sharePopupButton) {
                            shareIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_black);
                        } else if (view.getId() == R.id.aboutPopupButton) {
                            aboutIconHolder.setBackgroundResource(R.drawable.round_background_popup_window_black);
                        }
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (view.getId() == R.id.settingsPopupButton) {
                                settingsIconHolder.setBackgroundColor(getResources().getColor(R.color.transparent));
                            } else if (view.getId() == R.id.bookmarksPopupButton) {
                                bookmarksIconHolder.setBackgroundColor(getResources().getColor(R.color.transparent));
                            } else if (view.getId() == R.id.vpnPopupButton) {
                                vpnIconHolder.setBackgroundColor(getResources().getColor(R.color.transparent));
                            } else if (view.getId() == R.id.themesPopupButton) {
                                themesIconHolder.setBackgroundColor(getResources().getColor(R.color.transparent));
                            } else if (view.getId() == R.id.downloadPopupButton) {
                                downloadIconHolder.setBackgroundColor(getResources().getColor(R.color.transparent));
                            } else if (view.getId() == R.id.incognitoPopupButton) {
                                incognitoIconHolder.setBackgroundColor(getResources().getColor(R.color.transparent));
                            } else if (view.getId() == R.id.historyPopupButton) {
                                historyIconHolder.setBackgroundColor(getResources().getColor(R.color.transparent));
                            } else if (view.getId() == R.id.sharePopupButton) {
                                shareIconHolder.setBackgroundColor(getResources().getColor(R.color.transparent));
                            } else if (view.getId() == R.id.aboutPopupButton) {
                                aboutIconHolder.setBackgroundColor(getResources().getColor(R.color.transparent));
                            }
                        }
                    }, 150);
                }
            }
        });
    }

    public void setColor(TextView text, int value){
        if(value==1){
            text.setTextColor(getResources().getColor(R.color.black));
        }else{
            text.setTextColor(getResources().getColor(R.color.white));
        }

    }
    public  void setAnimationLottie(LottieAnimationView lottieAnimationView){
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
    }

    public void exit(){
        System.exit(0);
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("message",message);
        setResult(RESULT_OK,returnIntent);
        overridePendingTransition(R.anim.hold,R.anim.slide_out_bottom);
        super.finish();

    }
}