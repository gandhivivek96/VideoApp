package com.example.vivekgandhi.videoapp;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import static android.R.attr.path;

public class VideoplayActivity extends AppCompatActivity {
String path;
    VideoView vidView1;
    String vidAddress1;
    Uri vidUri;
    MediaController vidControl1;
    private static final String TAG = "MyMsg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);

        Bundle firstData = getIntent().getExtras();
        path= firstData.getString("path");
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory()+path);

        vidView1.setVideoURI(uri);

        vidControl1 = new MediaController(this);
        vidControl1.setAnchorView(vidView1);
        vidView1.setMediaController(vidControl1);
        vidView1.start();
        Log.i(TAG,"After start");
    }
}
