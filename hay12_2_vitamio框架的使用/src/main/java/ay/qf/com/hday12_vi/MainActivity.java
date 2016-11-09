package ay.qf.com.hday12_vi;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * 通过VideoView播放视频的方式
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButStart;
    private VideoView mVideoview;
    private Button mButStop;
    private Button mButPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //进行框架的初始化判断，如果没有进行过初始化，那么就进行初始化操作
        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mButStart = (Button) findViewById(R.id.but_start);
        mVideoview = (VideoView) findViewById(R.id.videoview);

        mButStart.setOnClickListener(this);
        mButStop = (Button) findViewById(R.id.but_stop);
        mButStop.setOnClickListener(this);
        mButPause = (Button) findViewById(R.id.but_pause);
        mButPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_start:

                //添加在线视频的网址,并开始播放视频
                mVideoview.setVideoPath("http://wvideo.spriteapp.cn/video/2016/0610/575add617fc8b_wpd.mp4");

                //播放本地视频文件：
//                mVideoview.setVideoURI(Uri.parse("/mnt/sdcard/***.mp4"));

                //设置在视频的下方显示可拖动的进度条以及播放时间等
                mVideoview.setMediaController(new MediaController(this));



                //获取焦点
//                mVideoview.requestFocus();
                //设置视频播放的准备时的监听事件
                mVideoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                   //当视频准备时调用的方法
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //设置视频以几倍速的速率播放 ，范围为：0.5f--2f
                        mp.setPlaybackSpeed(2.0f);
                    }
                });

                break;
            case R.id.but_stop:
                mVideoview.stopPlayback(); // 停止


                break;
            case R.id.but_pause:
                mVideoview.pause(); // 暂停
                break;
        }
    }
}
