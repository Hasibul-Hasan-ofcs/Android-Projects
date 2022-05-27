package com.example.polygon;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Today_history_fragment extends Fragment {

    private ListView historyListViewToday;
    private History_SQLiteHelper history_sqLiteHelper;
    public int todayCounter=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_history_fragment, container, false);

        historyListViewToday = (ListView) view.findViewById(R.id.historyListViewToday);

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
            String[] titleToday = new String[cursor.getCount()],urlToday = new String[cursor.getCount()],
                    dateToday = new String[cursor.getCount()];

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

                    if (dateSQLiteValue == currentDate) {
                        titleToday[i] = titleDBH[i];
                        urlToday[i] = urlDBH[i];
                        todayCounter++;
                    }
//                    tempH = cursor.getBlob(2);
//                    imageDBH[i] = BitmapFactory.decodeByteArray(tempH,0, tempH.length);
                    i--;
                }

                if (todayCounter > 0) {
//                    Log.d("tag", Arrays.toString(titleToday) +""+ Arrays.toString(urlToday));
                    HistoryActivityCustomAdapter historyActivityCustomAdapter = new HistoryActivityCustomAdapter(getActivity(), titleToday, urlToday);
                    historyListViewToday.setAdapter(historyActivityCustomAdapter);
                }
            }
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return view;
    }
}