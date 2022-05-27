package com.example.polygon;

import androidx.fragment.app.Fragment;

import android.content.res.AssetManager;
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

public class welcome_welcome_fragment extends Fragment {

    VideoView videoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome_welcome_fragment, container, false);
        TextView welcomeLarge = (TextView) view.findViewById(R.id.welcomeLargeWW);
        TextView toLarge = (TextView) view.findViewById(R.id.toLargeWW);
        videoView = (VideoView) view.findViewById(R.id.videoBackgroundViewWW);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "font/DroidLogo_Bold.ttf");
//        welcomeLarge.setTypeface(font);
//        toLarge.setTypeface(font);

        //play background video
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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
        videoView.setVideoURI(uri);
        videoView.start();

        return  view;

    }
}