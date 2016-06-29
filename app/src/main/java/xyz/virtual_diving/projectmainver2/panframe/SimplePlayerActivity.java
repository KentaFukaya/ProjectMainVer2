/*
 * SimplePlayer
 * Android example of Panframe library
 * The example plays back an panoramic movie from a resource.
 * 
 * (c) 2012-2013 Mindlight. All rights reserved.
 * Visit www.panframe.com for more information. 
 * 
 */

package xyz.virtual_diving.projectmainver2.panframe;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


import com.panframe.android.lib.PFAsset;
import com.panframe.android.lib.PFAssetObserver;
import com.panframe.android.lib.PFAssetStatus;
import com.panframe.android.lib.PFHotspot;
import com.panframe.android.lib.PFHotspotClickListener;
import com.panframe.android.lib.PFNavigationMode;
import com.panframe.android.lib.PFObjectFactory;
import com.panframe.android.lib.PFView;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import xyz.virtual_diving.projectmainver2.R;

public class SimplePlayerActivity extends FragmentActivity implements PFAssetObserver, OnSeekBarChangeListener, PFHotspotClickListener {

    PFView _pfview;
    PFAsset _pfasset;
    PFNavigationMode _currentNavigationMode = PFNavigationMode.MOTION;

    boolean _updateThumb = true;

    Timer _scrubberMonitorTimer;

    ViewGroup _frameContainer;
    Button _stopButton;
    Button _playButton;
    Button _touchButton;
    SeekBar _scrubber;
    OrientationEventListener ol;

    //20160623 kentafukaya add
    float PushPlayButtonTime;
    File file0 = new File(Environment.getExternalStorageDirectory() + "/capture0.png");


    /**
     * Creation and initalization of the Activitiy.
     * Initializes variables, listeners, and starts request of a movie list.
     *
     * @param savedInstanceState a saved instance of the Bundle
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.simpleplayer_main);

        _frameContainer = (ViewGroup) findViewById(R.id.framecontainer);
        _frameContainer.setBackgroundColor(0xFFFFFFFF);

        /*各ボタンのID取得*/
        _playButton = (Button) findViewById(R.id.playbutton);
        _stopButton = (Button) findViewById(R.id.stopbutton);
        _touchButton = (Button) findViewById(R.id.touchbutton);
        _scrubber = (SeekBar) findViewById(R.id.scrubber);

        /*各ボタンのクリックリスナ*/
        _playButton.setOnClickListener(playListener);
        _stopButton.setOnClickListener(stopListener);
        _touchButton.setOnClickListener(touchListener);
        _scrubber.setOnSeekBarChangeListener(this);

        _scrubber.setEnabled(false);
        showControls(true);


        showControls(true);

        /*------------------------------------------------------------------------------------*/
        //20160623 kentafukaya add
        //参考にしたサイト: http://blog.lciel.jp/blog/2014/02/08/android-about-storage/

        // 指定したファイル名が無ければ作成する。
        file0.getParentFile().mkdir();

    }


    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (_pfview != null)
            _pfview.handleOrientationChange();
    }

    /**
     * Show/Hide the playback controls
     *
     * @param bShow Show or hide the controls. Pass either true or false.
     */
    public void showControls(boolean bShow) {
        int visibility = View.GONE;

        if (bShow)
            visibility = View.VISIBLE;

        _playButton.setVisibility(visibility);
        _stopButton.setVisibility(visibility);
        _touchButton.setVisibility(visibility);
        _scrubber.setVisibility(visibility);

        if (_pfview != null) {
            if (!_pfview.supportsNavigationMode(PFNavigationMode.MOTION))
                _touchButton.setVisibility(View.GONE);
        }
    }


    /**
     * Start the video with a local file path
     *
     * @param filename The file path on device storage
     */
    public void loadVideo(String filename) {

        _pfview = PFObjectFactory.view(this);
        _pfasset = PFObjectFactory.assetFromUri(this, Uri.parse(filename), this);
        _pfview.getView();
        _pfview.displayAsset(_pfasset);
        _pfview.setNavigationMode(_currentNavigationMode);

        /*
        _pfview.setBlindSpotImage(BitmapFactory.decodeResource(getResources(), R.raw.blackspot));
        _pfview.setBlindSpotPosition(1);
        _pfview.setBlindSpotScale(1.5f);
        */
        
        PFHotspot hp1 = _pfview.createHotspot(BitmapFactory.decodeResource(getResources(), R.raw.hotspot));
        hp1.setTag(10);
        hp1.setCoordinates(60, 40, 0);
        hp1.setClickListener(this);

        /*
        PFHotspot hp2 = _pfview.createHotspot(BitmapFactory.decodeResource(getResources(), R.raw.hotspot));
        hp2.setTag(20);
        hp2.setCoordinates(0, 40, 0);
        hp2.setClickListener(this);
        */
        _frameContainer.addView(_pfview.getView(), 0);
    }

    /**
     * Status callback from the PFAsset instance.
     * Based on the status this function selects the appropriate action.
     *
     * @param asset  The asset who is calling the function
     * @param status The current status of the asset.
     */
    public void onStatusMessage(final PFAsset asset, PFAssetStatus status) {
        switch (status) {
            case LOADED:
                Log.d("SimplePlayer", "Loaded");
                break;
            case DOWNLOADING:
                Log.d("SimplePlayer", "Downloading 360� movie: " + _pfasset.getDownloadProgress() + " percent complete");
                break;
            case DOWNLOADED:
                Log.d("SimplePlayer", "Downloaded to " + asset.getUrl());
                break;
            case DOWNLOADCANCELLED:
                Log.d("SimplePlayer", "Download cancelled");
                break;
            case PLAYING:
                Log.d("SimplePlayer", "Playing");
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                Log.d("TEST", "onStatusMessage: ");
                _scrubber.setEnabled(true);
                _scrubber.setMax((int) asset.getDuration());
                _playButton.setText("pause");
                if (_scrubberMonitorTimer == null) {
                    _scrubberMonitorTimer = new Timer();
                    final TimerTask task = new TimerTask() {
                        public void run() {
                            if (_updateThumb)
                                _scrubber.setProgress((int) asset.getPlaybackTime());
                        }
                    };
                    _scrubberMonitorTimer.schedule(task, 0, 33);
                }
                break;
            case PAUSED:
                Log.d("SimplePlayer", "Paused");
                _playButton.setText("play");
                _pfview.injectImageFromResource(R.raw.pausescreen);
                break;
            case STOPPED:
                Log.d("SimplePlayer", "Stopped");
                _playButton.setText("play");
                _scrubberMonitorTimer.cancel();
                _scrubberMonitorTimer.purge();
                _scrubberMonitorTimer = null;
                _scrubber.setProgress(0);
                _scrubber.setEnabled(false);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                break;
            case COMPLETE:
                Log.d("SimplePlayer", "Complete");
                _playButton.setText("play");
                _scrubberMonitorTimer.cancel();
                _scrubberMonitorTimer.purge();
                _scrubberMonitorTimer = null;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                break;
            case ERROR:
                Log.d("SimplePlayer", "Error");
                break;
        }
    }

    /**
     * Click listener for the play/pause button
     */
    private OnClickListener playListener = new OnClickListener() {
        public void onClick(View v) {

            if (_pfasset == null)
                loadVideo("android.resource://" + getPackageName() + "/" + R.raw.sakana2);

            if (_pfasset.getStatus() == PFAssetStatus.PLAYING) {
                PushPlayButtonTime = _pfasset.getPlaybackTime();
                _pfasset.pause();
            } else {
                if (_pfview != null) {
                    _pfview.injectImage(null);
                }
                _pfasset.setPLaybackTime(PushPlayButtonTime);
                _pfasset.play();
            }
            Log.d("TEST", "PushPlayButton = "+PushPlayButtonTime);//現在の再生時間の取得
        }
    };

    /**
     * Click listener for the stop/back button
     */

    private OnClickListener stopListener = new OnClickListener() {
        public void onClick(View v) {

            if (_pfasset == null) {
                finish();
                return;
            } else {

                _pfasset.stop();

                // cleanup
                _pfview.release();
                _pfasset.release();

                _pfasset = null;

                _frameContainer.removeView(_pfview.getView());
                _pfview = null;
                finish();
            }
        }
    };

    /**
     * Click listener for the navigation mode (touch/motion (if available))
     */
    private OnClickListener touchListener = new OnClickListener() {
        public void onClick(View v) {
            if (_pfview != null) {
                Button touchButton = (Button) findViewById(R.id.touchbutton);
                if (_currentNavigationMode == PFNavigationMode.TOUCH) {
                    _currentNavigationMode = PFNavigationMode.MOTION;
                    touchButton.setText("motion");
                } else {
                    _currentNavigationMode = PFNavigationMode.TOUCH;
                    touchButton.setText("touch");
                }
                _pfview.setNavigationMode(_currentNavigationMode);
            }
        }
    };

    /**
     * Setup the options menu
     *
     * @param menu The options menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_player, menu);
        return true;
    }

    /**
     * Called when pausing the app.
     * This function pauses the playback of the asset when it is playing.
     */
    public void onPause() {
        super.onPause();
        if (_pfasset != null) {
            if (_pfasset.getStatus() == PFAssetStatus.PLAYING)
                _pfasset.pause();
        }
    }

    /**
     * Called when a previously created loader is being reset, and thus making its data unavailable.
     *
     * @param seekbar  The SeekBar whose progress has changed
     * @param progress The current progress level.
     * @param fromUser True if the progress change was initiated by the user.
     */
    public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
    }

    /**
     * Notification that the user has started a touch gesture.
     * In this function we signal the timer not to update the playback thumb while we are adjusting it.
     *
     * @param seekbar The SeekBar in which the touch gesture began
     */
    public void onStartTrackingTouch(SeekBar seekbar) {
        _updateThumb = false;
    }

    /**
     * Notification that the user has finished a touch gesture.
     * In this function we request the asset to seek until a specific time and signal the timer to resume the update of the playback thumb based on playback.
     *
     * @param seekbar The SeekBar in which the touch gesture began
     */
    public void onStopTrackingTouch(SeekBar seekbar) {
        _pfasset.setPLaybackTime(seekbar.getProgress());
        _updateThumb = true;
    }


    /**
     * Notification that the user has touched a hotspot.
     *
     * @param hotspot The hotspot that was touched or triggered.
     */
    public void onClick(PFHotspot hotspot) {
        hotspot.animate();
		hotspot.setEnabled(false);
        Log.d("SimplePlayer", "Hotspot clicked: " + hotspot.getTag());
        //saveCapture(this.getWindow().getDecorView(),file);//キャプチャーの取得
    }


    /*------------------------------------------------------------------------------------------------*/
    /*20160623 KentaFukaya
    * 画面キャプチャーを取るために、増やしました。
*/


    /**
     * 撮ったキャプチャを保存
     * @param view
     * @param file output
     */
    public void saveCapture(View view, File file) {
        // キャプチャを撮る
        Bitmap capture = getViewCapture(view);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, false);
            // 画像のフォーマットと画質と出力先を指定して保存
            capture.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Log.d("TEST", "saveCapture: OK");
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ie) {
                    fos = null;
                }
            }
        }
    }

    /**
     * キャプチャを撮る
     * @param view
     * @return 撮ったキャプチャ(Bitmap)
     */
    public Bitmap getViewCapture(View view) {
        view.setDrawingCacheEnabled(true);

        // Viewのキャプチャを取得
        Bitmap cache = view.getDrawingCache();
        if(cache == null){
            return null;
        }
        Bitmap screenShot = Bitmap.createBitmap(cache);
        view.setDrawingCacheEnabled(false);
        Log.d("TEST", "screenShot: OK");
        return screenShot;
    }


    /*
    * 内部ストレージから画像ファイルを読み込み表示する
    * //http://tech.jsa.co.jp/how_to_get_storage_path/
    */

    public void getExtraImgaeUrl(View view,int ImageViewId){
        ImageView img = (ImageView) view.findViewById(ImageViewId);
        String path = Environment.getExternalStorageDirectory().getPath();
        File dir = new File(path);
        File file = new File(dir.getAbsolutePath()+"/capture.png");
        if (file.exists()) {
            img.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
        }else {
            Log.d("TEST", "ERROR");
        }
    }

}
