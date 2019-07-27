package com.nikiss.issouf.expansion;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.android.vending.expansion.zipfile.ZipResourceFile;

import java.io.File;
import java.io.IOException;


public class TutoActivity extends AppCompatActivity {
    private static final String LOG_TAG = "TutoActivity";

    private static boolean isKill;

    private VideoView video;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuto);

        if(!"mounted".equals(Environment.getExternalStorageState())){
            text = (TextView) findViewById(R.id.info_text);
            text.setText("Problème d'accès à l'espace de stockage externe : " + Environment.getExternalStorageDirectory());

        }

        isObbFileExist();

        //le test qui permettra de voir si le main seulement existe sur le stockage externe
        if (!DownloaderActivity.expansionFilesDelivered(this)) {
            //Log.d("DOWNLOAD","LUNCH DOWNLOAD");
            final Intent downloadResourceIntent = new Intent(
                    TutoActivity.this,
                    DownloaderActivity.class);
            startActivity(downloadResourceIntent);
            overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
        }

        isKill = false;
    }

    private void isObbFileExist() {
        String packageName = "";
        //System.out.println("Package Name is: "+getApplicationContext().getPackageName());
        packageName = getApplicationContext().getPackageName();
        int version =1;
        final String[] expansionFilePath = {""};
        /* mObbFileName is the signed OBB file!!! */
        String mObbFilePath = Environment.getExternalStorageDirectory()
                + "/Android/obb/" + packageName + "/" + "main."
                + version + "." + packageName + ".obb";
        final File mainFile = new File(mObbFilePath);
        if (mainFile.exists()) {
            //Log.d("STORAGE", "FILE: " + mObbFilePath + " Exists");
        } else {
            //Log.d("STORAGE", "FILE: " + mObbFilePath + " DOESNT EXIST");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int videoName = 0;
        switch (item.getItemId()) {
            case R.id.menu_scrat_01 :
                videoName = R.string.video_scrat_01;
                break;
            case R.id.menu_scrat_02 :
                videoName = R.string.video_scrat_02;
                break;
        }

        video = (VideoView) findViewById(R.id.video_view);
        video.setVideoURI(Uri.parse(getUriForVideo(videoName)));
        video.setMediaController(new MediaController(this));
        video.setVisibility(VideoView.VISIBLE);
        video.start();

        text = (TextView) findViewById(R.id.info_text);
        text.setVisibility(TextView.INVISIBLE);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        isKill = true;
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        if (isKill) {
            finish();
        }
        super.onResume();
    }

    private String getUriForVideo(int nameVideo) {
        SampleZipFileProvider contentProvider = new SampleZipFileProvider();
        String authority = contentProvider.getAuthority();
        Uri contentUri = Uri.parse("content://" + authority);
        String videoName = null;

        try {
            int versionCode = DownloaderActivity.initialAPKVersionCode;
            ZipResourceFile expansionFile = APKExpansionSupport
                    .getAPKExpansionZipFile(this, versionCode, versionCode);
            if (expansionFile != null) {
                String videoFileName = getResources().getString(nameVideo);
                ZipResourceFile.ZipEntryRO[] ziro = expansionFile.getAllEntries();
                for (ZipResourceFile.ZipEntryRO entry : ziro) {
                    if (entry.mFileName.equals(videoFileName)) {
                        videoName = entry.mFileName;
                    }
                }
            }
        } catch (IOException e) {
            //Log.e(LOG_TAG, "Fichier d'extension principal introuvable");
        }
        return contentUri + "/" + videoName;
    }
}
