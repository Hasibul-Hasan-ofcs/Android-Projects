package com.example.polygon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Settings_Activity extends AppCompatActivity {

    private ImageButton exitButton;
    private ListView settingsListView;
    public String[] stringList;
    public int[] icons = {R.drawable.icon_search_white_50px,R.drawable.icon_browse_white_50px,
            R.drawable.icon_download_white_50px,R.drawable.icons8_notification_white_50px_2,
            R.drawable.icons8_account_white_50px,R.drawable.icons8_language_white_50px_1,
            R.drawable.icons8_priority_white_50px};
    public int[] icons2 = {R.drawable.icon_search_black_50px,R.drawable.icon_browse_black_50px,
            R.drawable.icon_download_black_50px,R.drawable.icons8_notification_black_50px_1,
            R.drawable.icons8_account_black_50px_1,R.drawable.icons8_language_black_50px,
            R.drawable.icon_priority_black_50px_1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.deep_color_03));

        stringList = getResources().getStringArray(R.array.settings_array);

        exitButton = findViewById(R.id.exitButton);
        settingsListView = findViewById(R.id.settingsListView);

//        custom adapter
        SettingsAcivityCustomAdapter adapter = new SettingsAcivityCustomAdapter(this,stringList,icons);
        settingsListView.setAdapter(adapter);

        settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Settings_Activity.this, "clicked"+position, Toast.LENGTH_SHORT).show();
            }
        });


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold,R.anim.slide_out_bottom);
    }
}