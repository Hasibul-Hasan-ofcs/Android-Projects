package com.example.polygon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Ask_exit_popup_activity extends AppCompatActivity {

    private Button exitCancelButton, exitYesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_exit_popup);

        //      set popup window(Resolution,position)

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        getWindow().setLayout((int)(width*0.9),(int)(height*0.4));

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

        exitYesButton = findViewById(R.id.exitYesButton);
        exitCancelButton = findViewById(R.id.exitCancelButton);

        overridePendingTransition(R.anim.hold,R.anim.slide_out_bottom);

        exitCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        exitYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sharedPreferencesExit = getSharedPreferences("ask_exit", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editExitOption = sharedPreferencesExit.edit();
//                editExitOption.putString("decision", "exit");
//                editExitOption.commit();
//
//                Intent popupIntent = new Intent(Ask_exit_popup_activity.this,Pre_Browser_Main.class);
//                startActivity(popupIntent);
//                finish();
                System.exit(0);
            }
        });
    }
}