package xyz.virtual_diving.projectmainver2.Image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import xyz.virtual_diving.projectmainver2.R;

public class ImageActivity extends AppCompatActivity {
    private final ArrayList<Bitmap> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);

        // ループ回数10(要検討)
        for (int id = 0; id <= 10; id++) {
            new AsyncTask<String, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(String... params) {
                    Bitmap image = null;
                    BitmapFactory.Options options;
                    try {
                        // インターネット上の画像を取得して、Bitmap に変換
                        URL url = new URL(params[0]);
                        options = new BitmapFactory.Options();
                        // 実際に読み込む
                        options.inJustDecodeBounds = false;
                        InputStream is = (InputStream) url.getContent();
                        image = BitmapFactory.decodeStream(is, null, options);
                        is.close();
                        list.add(image);
                    } catch (Exception e) {
                        Log.i("error: ", e.getMessage());
                    }
                    return image;
                }

                // UI スレッド処理
                @Override
                protected void onPostExecute(Bitmap image) {
                    super.onPostExecute(image);

                    // gridView に list を追加
                    BitmapAdapter adapter = new BitmapAdapter(getApplicationContext(), R.layout.image_items, list);
                    GridView gridView = (GridView) findViewById(R.id.gridView);
                    gridView.setAdapter(adapter);
                }
            }.execute("https://mb.api.cloud.nifty.com/2013-09-01/applications/xenlIaKJArb0UrG/publicFiles/picture" + id + ".jpg");
        }
    }
}
