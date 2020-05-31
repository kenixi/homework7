package com.bytedance.videoplayer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("basic");

        videoView = findViewById(R.id.videoView);

        videoView.setVideoPath(getVideoPath(R.raw.class7));

        MediaController mediaController = new MediaController(this);

        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("progress", videoView.getCurrentPosition());
        savedInstanceState.putBoolean("playing", videoView.isPlaying());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int position = savedInstanceState.getInt("progress");
        boolean playing = savedInstanceState.getBoolean("playing");

        videoView.seekTo(position);
        if (playing)
            videoView.start();

        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //横屏
            getSupportActionBar().hide();
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //竖屏
            getSupportActionBar().show();
        }
    }

    private String getVideoPath(int resId) {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }
}
