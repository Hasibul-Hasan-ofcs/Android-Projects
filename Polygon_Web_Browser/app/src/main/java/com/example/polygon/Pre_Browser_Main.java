package com.example.polygon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Pre_Browser_Main extends AppCompatActivity {

    public ImageButton googleSearchButton01, audioSearchButton;
    public Button googleCardButton, facebookCardButton, twitterCardButton, whatsappCardButton, youtubeCardButton, gmailCardButton, hotstarCardButton, skypeCardButton, animekisaCardButton, alibabaCardButton, googlePlayCardButton;
    public FloatingActionButton floatingActionButton_01;
    public CardView googleIconCardView, facebookIconCardView, twitterIconCardView, whatsappIconCardView, youtubeIconCardView, gmailIconCardView, hotstarIconCardView, skypeIconCardView, animekisaIconCardView, alibabaIconCardView, googlePlayIconCardView, addMoreIconCardView;
    public Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_browser_main);

        try {

            Window window = getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.deep_color_03));

//          find by id
            floatingActionButton_01 = findViewById(R.id.floatingActionButton_01);
            googleSearchButton01 = findViewById(R.id.googleSearchButton01);
            googleCardButton = findViewById(R.id.googleCardButton);
            facebookCardButton = findViewById(R.id.facebookCardButton);
            twitterCardButton = findViewById(R.id.twitterCardButton);
            whatsappCardButton = findViewById(R.id.whatsappCardButton);
            youtubeCardButton = findViewById(R.id.youtubeCardButton);
            gmailCardButton = findViewById(R.id.gmailCardButton);
            hotstarCardButton = findViewById(R.id.hotstarCardButton);
            skypeCardButton = findViewById(R.id.skypeCardButton);
            animekisaCardButton = findViewById(R.id.animekisaCardButton);
            alibabaCardButton = findViewById(R.id.aliCardButton);
            googlePlayCardButton = findViewById(R.id.googlePlayCardButton);
            searchButton = findViewById(R.id.searchButton);

            googleIconCardView = findViewById(R.id.googleIconCardView);
            facebookIconCardView = findViewById(R.id.facebookIconCardView);
            twitterIconCardView = findViewById(R.id.twitterIconCardView);
            whatsappIconCardView = findViewById(R.id.whatsappIconCardView);
            youtubeIconCardView = findViewById(R.id.youtubeIconCardView);
            gmailIconCardView = findViewById(R.id.gmailIconCardView);
            hotstarIconCardView = findViewById(R.id.hotstarIconCardView);
            skypeIconCardView = findViewById(R.id.skypeIconCardView);
            animekisaIconCardView = findViewById(R.id.animekisaIconCardView);
            alibabaIconCardView = findViewById(R.id.alibabaIconCardView);
            googlePlayIconCardView = findViewById(R.id.googlePlayIconCardView);
            addMoreIconCardView = findViewById(R.id.addMoreIconCardView);

            audioSearchButton = findViewById(R.id.audioSearchButton);

//          initializing onclickListener
            ImageButton[] imgButton01 = {googleSearchButton01};
            Button[] btnArray01 = {googleCardButton, facebookCardButton, twitterCardButton, whatsappCardButton, youtubeCardButton, gmailCardButton, hotstarCardButton, skypeCardButton, animekisaCardButton, alibabaCardButton, googlePlayCardButton};
            String google = "google", facebook = "facebook", twitter = "twitter", whatsapp = "whatsapp", youtube = "youtube", gmail = "gmail", hotstar = "hotstar", skype = "skype", animekisa = "animekisa", alibaba = "alibaba", googlePlay = "googlePlay";
            String[] strArray01 = {google, facebook, twitter, whatsapp, youtube, gmail, hotstar, skype, animekisa, alibaba, googlePlay};
            CardView[] card = {googleIconCardView, facebookIconCardView, twitterIconCardView, whatsappIconCardView, youtubeIconCardView, gmailIconCardView, hotstarIconCardView, skypeIconCardView, animekisaIconCardView, alibabaIconCardView, googlePlayIconCardView};

            for(int i=0; i<btnArray01.length;i++){
                onclickInitialize(btnArray01[i], strArray01[i], card[i]);
            }
            for(int i=0; i<imgButton01.length;i++){
                onclickInitialize(imgButton01[i], google);
            }


//            Bundle bundleExit = getIntent().getExtras();
//            String decision = bundleExit.getString("decision");
//            if(decision.contains("exit")){
//                finish();
//            }
//            SharedPreferences sharedPreferencesExit = getSharedPreferences("ask_exit", Context.MODE_PRIVATE);
//            if(sharedPreferencesExit.contains("decision")) {
//                String themeValue = sharedPreferencesExit.getString("decision", "nothing found");
//                if(themeValue.contains("exit")){
//                    SharedPreferences sharedPreferencesExitInternal = getSharedPreferences("ask_exit", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editExitOption = sharedPreferencesExitInternal.edit();
//                    editExitOption.putString("decision", "do_not_exit");
//                    editExitOption.commit();
//                    System.exit(0);
//                }
//            }


        }catch (Exception e){
            Toast.makeText(Pre_Browser_Main.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popupIntent = new Intent(Pre_Browser_Main.this,System_SearchBar_Popup_Activity.class);
                popupIntent.putExtra("currentWebUrl","");
                startActivity(popupIntent);
                overridePendingTransition(R.anim.slide_in_bottom,R.anim.hold);
            }
        });



//        voice search
        audioSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    displaySpeechRecognizer();
            }
        });



        floatingActionButton_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    Intent popupIntent = new Intent(Pre_Browser_Main.this,System_Popup_Activity.class);
                    startActivity(popupIntent);
                    overridePendingTransition(R.anim.slide_in_bottom,R.anim.hold);

                }catch (Exception e){
                    Log.d("tag","Exception occurred");
                }
//                Toast.makeText(BrowserMainActivity.this,"popup_Active",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void onclickInitialize(Button btn,String value, CardView card){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card.setCardBackgroundColor(getResources().getColor(R.color.white));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        card.setCardBackgroundColor(getResources().getColor(R.color.deep_color_03));
                    }
                }, 100);
                Intent gIntent = new Intent(Pre_Browser_Main.this,BrowserMainActivity.class);
                if(value.contains("hotstar")){
                    gIntent.putExtra("hotstar",""+value+"");
                }else if(value.contains("animekisa")){
                    gIntent.putExtra("animekisa",""+value+"");
                }else{
                    gIntent.putExtra("url",""+value+"");
                }

                startActivity(gIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

//                    ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(Pre_Browser_Main.this, mkpair);
//                    startActivity(gIntent, op.toBundle());
            }
        });
    }
    public  void onclickInitialize(ImageButton btn,String value){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(value!=null){
                    Intent gIntent = new Intent(Pre_Browser_Main.this,BrowserMainActivity.class);
                    gIntent.putExtra("url",""+value+"");
                    startActivity(gIntent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
                }else{
                    Toast.makeText(Pre_Browser_Main.this, "oops! did you just forgot to enter your keywords!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hello Say Something");
// This starts the activity and populates the intent with the speech text.
        try{
            startActivityForResult(intent,1);
        }catch (ActivityNotFoundException e){
            Toast.makeText(Pre_Browser_Main.this,"This device do not support speech recognition",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if( resultCode==RESULT_OK && data!=null){
                    ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String voice = arrayList.get(0);
//                    Toast.makeText(Pre_Browser_Main.this, voice, Toast.LENGTH_SHORT).show();
                    Intent gIntent = new Intent(Pre_Browser_Main.this,BrowserMainActivity.class);
                    gIntent.putExtra("voice",""+voice+"");
                    startActivity(gIntent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }else{
                    Toast.makeText(Pre_Browser_Main.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }


    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//                                    Intent data) {
//        if ((requestCode == 1) && (resultCode == RESULT_OK) && (data!=null)) {
//            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            String spokenText = results.get(0);
//            // Do something with spokenText.
//            Toast.makeText(Pre_Browser_Main.this, spokenText, Toast.LENGTH_SHORT).show();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    public void onBackPressed(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you really want to exist?");
            builder.setTitle("Alert");
            builder.setIcon(getResources().getDrawable(R.drawable.question_mark));
            builder.setCancelable(false);

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Pre_Browser_Main.super.onBackPressed();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();

            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.deep_color_03));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.deep_color_03));
                }
            });

            alertDialog.show();
//        }

    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold,R.anim.slide_out_right);
    }
}