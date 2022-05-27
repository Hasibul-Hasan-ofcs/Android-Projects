package com.example.polygon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.androidbrowserhelper.playbilling.digitalgoods.ItemDetails;

import java.util.ArrayList;
import java.util.List;

public class Bookmarks_Activity extends AppCompatActivity {

    private ImageButton exitButton;
    private ListView listViewBookmarks;
    private Bookmark_SQLiteHelper bookmark_sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.deep_color_03));

        exitButton = findViewById(R.id.exitButton);
        listViewBookmarks = findViewById(R.id.listViewBookmarks);

        try {

//        retrieving data
            bookmark_sqLiteHelper = new Bookmark_SQLiteHelper(Bookmarks_Activity.this);
            Cursor cursor = bookmark_sqLiteHelper.getCursor();
            String[] titleDB = new String[cursor.getCount()];
            String[] urlDB = new String[cursor.getCount()];
            Bitmap[] imageDB = new Bitmap[cursor.getCount()];

            if(cursor.getCount()==0){
                Toast.makeText(Bookmarks_Activity.this,"no data is available in the database",Toast.LENGTH_SHORT).show();
            }else {

                int i = cursor.getCount()-1;
                byte[] temp;
                while (cursor.moveToNext()){
                    titleDB[i] = cursor.getString(0);
                    urlDB[i] = cursor.getString(1);
                    temp = cursor.getBlob(2);
                    imageDB[i] = BitmapFactory.decodeByteArray(temp,0, temp.length);
                    i--;
//                    Toast.makeText(Bookmarks_Activity.this, cursor.getString(0) + " hi " + urlDB[i] + " " + imageDB[i], Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(Bookmarks_Activity.this,i+"<-value of i", Toast.LENGTH_SHORT).show();
                BookmarksActivityCustomAdapter bookmarksActivityCustomAdapter = new BookmarksActivityCustomAdapter(this, titleDB, urlDB, imageDB);
                listViewBookmarks.setAdapter(bookmarksActivityCustomAdapter);

            }



            listViewBookmarks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = (TextView) view.findViewById(R.id.urlIdB);
                    String text = textView.getText().toString();

                    Intent bookmarkedIntent = new Intent(Bookmarks_Activity.this,BrowserMainActivity.class);
                    bookmarkedIntent.putExtra("full_Url",text);
                    startActivity(bookmarkedIntent);
                    overridePendingTransition(R.anim.slide_in_bottom,R.anim.hold);
                    finish();
//                    Toast.makeText(Bookmarks_Activity.this, text, Toast.LENGTH_SHORT).show();

                }
            });



        }catch (Exception e){
            Toast.makeText(Bookmarks_Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

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