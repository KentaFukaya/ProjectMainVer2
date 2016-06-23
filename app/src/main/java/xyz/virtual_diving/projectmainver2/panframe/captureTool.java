package xyz.virtual_diving.projectmainver2.panframe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by kenta on 16/06/23.
 */
public class captureTool {

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
            capture.compress(Bitmap.CompressFormat.PNG, 100, fos);
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
        return screenShot;
    }


    /*
    * 内部ストレージから画像ファイルを読み込み表示する
    * //http://tech.jsa.co.jp/how_to_get_storage_path/
    */

    public void  getExtraImgaeUrl(View view,int ImageViewId){
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
