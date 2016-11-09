package ay.qf.com.hday12_vi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.CenterLayout;
import io.vov.vitamio.widget.VideoView;

/**
 * 通过VideoView播放视频的其余控制
 */
public class TwoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private VideoView mVv;
    private CenterLayout mCenterlayout;

    private int num;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.activity_two);
        initView();

        pd = new ProgressDialog(this);
        pd.setMessage("loadding!!!");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(100);

        mVv.setVideoPath("http://wvideo.spriteapp.cn/video/2016/0610/575add617fc8b_wpd.mp4");

        //设置加载进度相关的监听事件
        mVv.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch(what){
                case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                    //开始进行缓冲
                    if(mVv.isPlaying()) {
                        mVv.pause();
                    }
                    pd.show();
                break;
                case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    //缓冲结束
                    mVv.start();
                    pd.dismiss();
                break;
                }
                return true;
            }
        });

        mVv.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
//           每当加载进度改变时，都会调用此方法
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.i("tmd", "onBufferingUpdate: "+percent);
                pd.setProgress(percent);
            }
        });

//        mVv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                 optional need Vitamio 4.0
//                mediaPlayer.setPlaybackSpeed(1.0f);
//            }
//        });
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mVv = (VideoView) findViewById(R.id.vv);
        mCenterlayout = (CenterLayout) findViewById(R.id.centerlayout);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                num ++;
                if (num > 4 ) {
                    num =0;
                }
                switch (num) {
                    case 0:
                        //参数1： 布局的类型，参数2:宽高比，0为自动匹配宽高比
                        mVv.setVideoLayout(VideoView.VIDEO_LAYOUT_FIT_PARENT,0);
                        mButton.setText("VideoView.VIDEO_LAYOUT_FIT_PARENT");
                        break;
                    case 1:
                        mVv.setVideoLayout(VideoView.VIDEO_LAYOUT_ORIGIN,0);
                        mButton.setText("VideoView.VIDEO_LAYOUT_ORIGIN");
                        break;
                    case 2:
                        mVv.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE,0.5f);
                        mButton.setText("VideoView.VIDEO_LAYOUT_SCALE");
                        break;
                    case 3:
                        mVv.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH,0);
                        mButton.setText("VideoView.VIDEO_LAYOUT_STRETCH");
                        break;
                    case 4:
                        mVv.setVideoLayout(VideoView.VIDEO_LAYOUT_ZOOM,0);
                        mButton.setText("VideoView.VIDEO_LAYOUT_ZOOM");
                        break;
                }

                break;
        }
    }
}
