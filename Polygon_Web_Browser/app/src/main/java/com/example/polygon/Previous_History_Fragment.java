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


public class Previous_History_Fragment extends Fragment {

    private ListView historyListViewPrevious;
    private History_SQLiteHelper history_sqLiteHelper;
    public int previousCounter=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_previous__history_, container, false);

        historyListViewPrevious = (ListView) view.findViewById(R.id.historyListViewPrevious);

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
            String[] titlePrevious = new String[cursor.getCount()],urlPrevious = new String[cursor.getCount()],
                    datePrevious = new String[cursor.getCount()];

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

                    if (dateSQLiteValue < currentDate - 1) {
                        titlePrevious[i] = titleDBH[i];
                        urlPrevious[i] = urlDBH[i];
                        previousCounter++;
                    }
//                    tempH = cursor.getBlob(2);
//                    imageDBH[i] = BitmapFactory.decodeByteArray(tempH,0, tempH.length);
                    i--;
                }

                if (previousCounter > 0) {
//                    Log.d("tag", Arrays.toString(titleToday) +""+ Arrays.toString(urlToday));
                    HistoryActivityCustomAdapter historyActivityCustomAdapter = new HistoryActivityCustomAdapter(getActivity(), titlePrevious, urlPrevious);
                    historyListViewPrevious.setAdapter(historyActivityCustomAdapter);
                }
            }
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return view;
    }
}