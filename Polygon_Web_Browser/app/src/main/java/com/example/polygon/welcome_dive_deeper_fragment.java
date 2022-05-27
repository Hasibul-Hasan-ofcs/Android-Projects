package com.example.polygon;

import androidx.fragment.app.Fragment;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

public class welcome_dive_deeper_fragment extends Fragment {


    VideoView videoViewx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome_dive_deeper_fragment, container, false);
        TextView welcomeLarge = (TextView) view.findViewById(R.id.welcomeLargeDD);
        TextView toLarge = (TextView) view.findViewById(R.id.toLargeDD);
        videoViewx = (VideoView) view.findViewById(R.id.videoBackgroundViewDD);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "font/DroidLogo_Bold.ttf");
//        welcomeLarge.setTypeface(font);
//        toLarge.setTypeface(font);

        //play background video
        videoViewx.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                PlaybackParams myPlayBackParams;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    myPlayBackParams = new PlaybackParams();
                    myPlayBackParams.setSpeed(0.4f); //speed
                    mp.setPlaybackParams(myPlayBackParams);
                }
            }
        });

        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.polygon_background_black);
        videoViewx.setVideoURI(uri);
        videoViewx.start();

        return  view;
    }
}