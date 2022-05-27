package com.example.polygon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

public class System_SearchBar_Popup_Activity extends AppCompatActivity {

    public EditText searchView;
    public ImageButton forwardImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_search_bar_popup);

        Window window = getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.deep_color_03));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        getWindow().setLayout((int)(width*1.0),(int)(height*0.2));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.TOP;

//      to make behind dim (solution)
        params.dimAmount=0.7f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

//      to make popup behind color transparent
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        defines how the interface will be ordinated from center of the activity
        params.x=0;
        params.y=-10;

        getWindow().setAttributes(params);

//        find bt id
        forwardImage = findViewById(R.id.forwardImage);
        searchView = findViewById(R.id.searchView);

        Bundle fromBrowserMainActivity = getIntent().getExtras();
        String foundUrl = fromBrowserMainActivity.getString("currentWebUrl");
        searchView.setText(foundUrl);

        forwardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable value = searchView.getText();
                Toast.makeText(System_SearchBar_Popup_Activity.this, value, Toast.LENGTH_SHORT).show();

                if(value!=null) {
                    Intent gIntent = new Intent(System_SearchBar_Popup_Activity.this, BrowserMainActivity.class);
                    gIntent.putExtra("popupSearch", "" + value + "");
                    startActivity(gIntent);
                    finish();
                }else{
                    Toast.makeText(System_SearchBar_Popup_Activity.this, "oops! did you just forgot to enter your keywords!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void finish() {
        overridePendingTransition(R.anim.hold,R.anim.slide_out_bottom);
        super.finish();

    }
}