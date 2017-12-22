package com.example.taehyung.ddac;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.VideoView;

import com.example.taehyung.ddac.Fragment.DdacFragment;
import com.example.taehyung.ddac.Fragment.HomeFragment;
import com.example.taehyung.ddac.Fragment.UserFragment;

/**
 * Created by TaeHyungKim on 2017-12-10.
 */

public class SimpleVideoActivity extends AppCompatActivity {
    public static BottomNavigationView bottomNavigationView;
    private VideoView videoView;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_video);
    }
}
