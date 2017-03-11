package com.example.vivekgandhi.videoapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView vidView;
    private static final String TAG = "MyMessage";
    String vidAddress;
    Uri vidUri;
    MediaController vidControl;
    public static final int REQUEST_TAKE_GALLERY_VIDEO = 101;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      vidView = (VideoView)findViewById(R.id.myVideo);
       // vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
       // vidAddress = "https://youtu.be/co4YpHTqmfQ.mp4";
       // vidAddress = "https://redirector.googlevideo.com/videoplayback?source=youtube&requiressl=yes&mime=video%2Fmp4&mm=31&expire=1489228435&itag=22&mt=1489206742&mv=m&pl=48&ei=M37DWKvtNI7e4QLO0YeIDg&ms=au&upn=vXJ-S_FLNKI&ip=2001%3A19f0%3A7001%3Ad32%3A5400%3Aff%3Afe58%3A19e7&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&ratebypass=yes&id=o-ABhMYpz7XJ-oF2WUtgpgfuUL1-AqLxXCwHCp61kytOvV&ipbits=0&initcwndbps=867500&pcm2=yes&lmt=1471434394830324&key=yt6&mn=sn-a5m7lnez&dur=229.923&signature=47D6F71E70A05BBA862F172389F9BB776B411DAF.5B2725178A8FA73DB086924C45D7C10C2C550D39";
       // vidAddress = "android.resource://\" + getPackageName() + \"/\" + R.video.welcome);
       // vidView.setVideoURI(Uri.parse("android.resource://" +getPackageName()+ "/"+R.video.welcome));
      /*  Uri uri = Uri.parse(Environment.getExternalStorageDirectory()+"/documents/welcome.mp4");
        // vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(uri);

    vidControl = new MediaController(this);
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl);
        vidView.start();*/

        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG,"enter onactrsult");
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
                Uri selectedImageUri = data.getData();

                // OI FILE Manager
               String filemanagerstring = selectedImageUri.getPath();

                // MEDIA GALLERY
               String selectedImagePath = getPath(selectedImageUri);
                if (selectedImagePath != null) {

                    Intent intent = new Intent(this,
                            VideoplayActivity.class);
                    intent.putExtra("path", selectedImagePath);
                    Log.i(TAG,"b4 intent path");
                    startActivity(intent);
                    Log.i(TAG,"after intetn path");
                }
            }
        }
        Log.i(TAG," exitactrsult");
    }

    // UPDATED!
    public String getPath(Uri uri) {
        Log.i(TAG,"inside get path");
        String[] projection = { MediaStore.Video.Media.DATA };

        String[] proj = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        //Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            Log.i(TAG,"exit  if get path");
            return cursor.getString(column_index);
        } else{
            Log.i(TAG,"exit else get path");

            return null;}


    }

}
