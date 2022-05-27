package com.example.polygon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class History_Activity extends AppCompatActivity {

    private ListView historyListView;
    private ImageButton exitButtonHistoryActivity;
    private History_SQLiteHelper history_sqLiteHelper;
    private Button todayHistoryButton, yesterdayHistoryButton, previousHistoryButton;
    private int todayCounter=0, yesterdayCounter=0, previousCounter=0;;
    private Fragment fragment;
    public ListView historyListViewPrevious,historyListViewYesterday,historyListViewToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.deep_color_03));


        exitButtonHistoryActivity = findViewById(R.id.exitButtonHistoryActivity);
        todayHistoryButton = findViewById(R.id.todayHistoryButton);
        yesterdayHistoryButton = findViewById(R.id.yesterdayHistoryButton);
        previousHistoryButton = findViewById(R.id.previousHistoryButton);
//        historyListViewPrevious = findViewById(R.id.historyListViewPrevious);
//        historyListViewYesterday = findViewById(R.id.historyListViewYesterday);
//        historyListViewToday = findViewById(R.id.historyListViewToday);

//        try {
//
//            Date date = Calendar.getInstance().getTime();
//            SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
//            String cD = dFormat.format(date);
//            int currentDate = Integer.parseInt(cD);
////        retrieving data
//            history_sqLiteHelper = new History_SQLiteHelper(History_Activity.this);
//
//
//            Cursor cursor = history_sqLiteHelper.getCursor();
//            String[] titleDBH = new String[cursor.getCount()];
//            String[] urlDBH = new String[cursor.getCount()];
//            String[] dateDBH = new String[cursor.getCount()];
//
////            static variables for fragments
//            String[] titleToday = new String[cursor.getCount()],urlToday = new String[cursor.getCount()],
//                    dateToday = new String[cursor.getCount()],titleYesterday = new String[cursor.getCount()],
//                    urlYesterday = new String[cursor.getCount()],dateYesterday = new String[cursor.getCount()],
//                    titlePrevious = new String[cursor.getCount()],urlPrevious = new String[cursor.getCount()],
//                    datePrevious = new String[cursor.getCount()];
//
//
////            Bitmap[] imageDBH = new Bitmap[cursor.getCount()];
//
//
//            if(cursor.getCount()==0){
//                Toast.makeText(History_Activity.this,"no data is available in the database",Toast.LENGTH_SHORT).show();
//            }else {
//
//                int i = cursor.getCount()-1;
////                byte[] tempH;
//                while (cursor.moveToNext()){
//                    titleDBH[i] = cursor.getString(0);
//                    urlDBH[i] = cursor.getString(1);
//                    dateDBH[i] = cursor.getString(2);
//
//                    int dateSQLiteValue = Integer.parseInt(dateDBH[i]);
//
//                    if(dateSQLiteValue==currentDate){
//                        titleToday[i] = titleDBH[i];
//                        urlToday[i] = urlDBH[i];
//                        todayCounter++;
//                    }else if(dateSQLiteValue==currentDate-1){
//                        titleYesterday[i] = titleDBH[i];
//                        urlYesterday[i] = urlDBH[i];
//                        yesterdayCounter++;
//                    }else if(dateSQLiteValue<currentDate-1){
//                        titlePrevious[i] = titleDBH[i];
//                        urlPrevious[i] = urlDBH[i];
//                        previousCounter++;
//                    }
//
//
//
//
////                    tempH = cursor.getBlob(2);
////                    imageDBH[i] = BitmapFactory.decodeByteArray(tempH,0, tempH.length);
//                    i--;
//                }
////                Toast.makeText(Bookmarks_Activity.this,i+"<-value of i", Toast.LENGTH_SHORT).show();
////                HistoryActivityCustomAdapter historyActivityCustomAdapter= new HistoryActivityCustomAdapter(this, titleDBH, urlDBH, imageDBH);
//
//                if(todayCounter>0){
//                    Log.d("tag", Arrays.toString(titleToday) +""+ Arrays.toString(urlToday));
//                    HistoryActivityCustomAdapter historyActivityCustomAdapter= new HistoryActivityCustomAdapter(this, titleToday, urlToday);
//                    historyListViewToday.setAdapter(historyActivityCustomAdapter);
//                }else if(yesterdayCounter>0){
//                    Log.d("tag", Arrays.toString(titleToday) +""+ Arrays.toString(urlToday));
//                    HistoryActivityCustomAdapter historyActivityCustomAdapter= new HistoryActivityCustomAdapter(this, titleYesterday, urlYesterday);
//                    historyListViewYesterday.setAdapter(historyActivityCustomAdapter);
//                }else if(previousCounter>0){
//                    Log.d("tag", Arrays.toString(titleToday) +""+ Arrays.toString(urlToday));
//                    HistoryActivityCustomAdapter historyActivityCustomAdapter= new HistoryActivityCustomAdapter(this, titlePrevious, urlPrevious);
//                    historyListViewPrevious.setAdapter(historyActivityCustomAdapter);
//                }
//
//            }

            todayHistoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    todayHistoryButton.setBackgroundResource(R.drawable.history_button_customized_background_01);
                    yesterdayHistoryButton.setBackgroundResource(R.color.transparent);
                    previousHistoryButton.setBackgroundResource(R.color.transparent);
                    fragment = new Today_history_fragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                    fragmentTransaction.replace(R.id.historyActivityFragmentID01,fragment);
                    fragmentTransaction.commit();
                }
            });
            yesterdayHistoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    todayHistoryButton.setBackgroundResource(R.color.transparent);
                    yesterdayHistoryButton.setBackgroundResource(R.drawable.history_button_customized_background_01);
                    previousHistoryButton.setBackgroundResource(R.color.transparent);
                    fragment = new Yesterday_History_fragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                    fragmentTransaction.replace(R.id.historyActivityFragmentID01,fragment);
                    fragmentTransaction.commit();
                }
            });
            previousHistoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    todayHistoryButton.setBackgroundResource(R.color.transparent);
                    yesterdayHistoryButton.setBackgroundResource(R.color.transparent);
                    previousHistoryButton.setBackgroundResource(R.drawable.history_button_customized_background_01);
                    fragment = new Previous_History_Fragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                    fragmentTransaction.replace(R.id.historyActivityFragmentID01,fragment);
                    fragmentTransaction.commit();
                }
            });


//            historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    TextView textView = (TextView) view.findViewById(R.id.urlIdBH);
//                    String text = textView.getText().toString();
//
//                    Intent historyIntent = new Intent(History_Activity.this,BrowserMainActivity.class);
//                    historyIntent.putExtra("full_Url",text);
//                    startActivity(historyIntent);
//                    overridePendingTransition(R.anim.slide_in_bottom,R.anim.hold);
//                    finish();
//
//                }
//            });



//        }catch (Exception e){
//            Toast.makeText(History_Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }

        exitButtonHistoryActivity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

    }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold,R.anim.slide_out_left);
    }
}