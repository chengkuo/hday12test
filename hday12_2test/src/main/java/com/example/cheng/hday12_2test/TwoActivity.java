package com.example.cheng.hday12_2test;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by a452542253 on 2016/11/8.
 */

public class TwoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private VideoView mVv;
    private ProgressDialog pd;
    private int num = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.twoactivity);
        initView();
        pd = new ProgressDialog(this);
        pd.setMessage("Loading......");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(100);

        mVv.setVideoPath("http://wvideo.spriteapp.cn/video/2016/0610/575add617fc8b_wpd.mp4");

        mVv.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        if(mVv.isPlaying()){
                            mVv.pause();
                        }
                        pd.show();
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mVv.start();
                        pd.dismiss();
                        break;
                }
                return true;
            }
        });

        mVv.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                pd.setProgress(percent);

            }
        });
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mVv = (VideoView) findViewById(R.id.vv);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        num++;
        switch (v.getId()) {
            case R.id.button:
                if (num>4) {
                    num = 0;
                }
                switch (num){
                    case 0:
                        mVv.setVideoLayout(VideoView.VIDEO_LAYOUT_FIT_PARENT,0);
                        mButton.setText("VIDEO_LAYOUT_FIT_PARENT");
                        break;
                    case 1:
                        mVv.setVideoLayout(VideoView.VIDEO_LAYOUT_ORIGIN,0);
                        mButton.setText("VIDEO_LAYOUT_ORIGIN");
                        break;
                    case 2:
                        mVv.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE,0.5f);
                        mButton.setText("VIDEO_LAYOUT_SCALE");
                        break;
                    case 3:
                        mVv.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH,0);
                        mButton.setText("VIDEO_LAYOUT_STRETCH");
                        break;
                    case 4:
                        mVv.setVideoLayout(VideoView.VIDEO_LAYOUT_ZOOM,0);
                        mButton.setText("VIDEO_LAYOUT_ZOOM");
                        break;
                }
                break;
        }
    }
}
