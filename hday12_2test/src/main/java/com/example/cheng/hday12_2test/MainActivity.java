package com.example.cheng.hday12_2test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButStart;
    private VideoView mVideoview;
    private Button mButStop;
    private Button mButPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mButStart = (Button) findViewById(R.id.but_start);
        mVideoview = (VideoView) findViewById(R.id.videoview);
        mButStop = (Button) findViewById(R.id.but_stop);
        mButPause = (Button) findViewById(R.id.but_pause);

        mButStart.setOnClickListener(this);
        mButStop.setOnClickListener(this);
        mButPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_start:
                mVideoview.setVideoPath("http://wvideo.spriteapp.cn/video/2016/0610/575add617fc8b_wpd.mp4");
                //获取焦点
                mVideoview.requestFocus();

                mVideoview.setMediaController(new MediaController(this));
                mVideoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //设置视频以几倍速度播放  0.5~2.0之间
                        mp.setPlaybackSpeed(1.5f);
                    }
                });
                break;
            case R.id.but_stop:
                //停止
            mVideoview.stopPlayback();

                break;
            case R.id.but_pause:
                //暂停
                if (mVideoview.isPlaying()){
                    mVideoview.pause();
                } else {
                    mVideoview.start();
                }

                break;
        }
    }
}
