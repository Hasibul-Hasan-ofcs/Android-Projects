package com.example.polygon;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Yesterday_History_fragment extends Fragment {

    private ListView historyListViewYesterday;
    private History_SQLiteHelper history_sqLiteHelper;
    public int yesterdayCounter=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yesterday__history_fragment, container, false);

        historyListViewYesterday = (ListView) view.findViewById(R.id.historyListViewYesterday);


        try {

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            String cD = dFormat.format(date);
            int currentDate = Integer.parseInt(cD);
//        retrieving data
            history_sqLiteHelper = new History_SQLiteHelper(getActivity());


            Cursor cursor = history_sqLiteHelper.getCursor();
            String[] titleDBH = new String[cursor.getCount()];
            String[] urlDBH = new String[cursor.getCount()];
            String[] dateDBH = new String[cursor.getCount()];

//            static variables for fragments
            String[] titleYesterday = new String[cursor.getCount()],
                    urlYesterday = new String[cursor.getCount()], dateYesterday = new String[cursor.getCount()];

            if (cursor.getCount() == 0) {
                Toast.makeText(getActivity(), "no data is available in the database", Toast.LENGTH_SHORT).show();
            } else {

                int i = cursor.getCount() - 1;
//                byte[] tempH;
                while (cursor.moveToNext()) {
                    titleDBH[i] = cursor.getString(0);
                    urlDBH[i] = cursor.getString(1);
                    dateDBH[i] = cursor.getString(2);

                    int dateSQLiteValue = Integer.parseInt(dateDBH[i]);

                    if (dateSQLiteValue == currentDate - 1) {
                        titleYesterday[i] = titleDBH[i];
                        urlYesterday[i] = urlDBH[i];
                        yesterdayCounter++;
                    }
//                    tempH = cursor.getBlob(2);
//                    imageDBH[i] = BitmapFactory.decodeByteArray(tempH,0, tempH.length);
                    i--;
                }

                if (yesterdayCounter > 0) {
//                    Log.d("tag", Arrays.toString(titleToday) +""+ Arrays.toString(urlToday));
                    HistoryActivityCustomAdapter historyActivityCustomAdapter = new HistoryActivityCustomAdapter(getActivity(), titleYesterday, urlYesterday);
                    historyListViewYesterday.setAdapter(historyActivityCustomAdapter);
                }
            }
        }catch (Exception e){
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }


//        historyListViewYesterday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView textView = (TextView) view.findViewById(R.id.urlIdBH);
//                String text = textView.getText().toString();
//
//                Intent historyIntent = new Intent(Yesterday_History_fragment.this,BrowserMainActivity.class);
//                historyIntent.putExtra("full_Url",text);
//                startActivity(historyIntent);
////                overridePendingTransition(R.anim.slide_in_bottom,R.anim.hold);
////                finish();
//
//            }
//        });

        return view;

    }
}