                                                        package xyz.virtual_diving.projectmainver2.Image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import xyz.virtual_diving.projectmainver2.R;

public class ImageActivity extends AppCompatActivity {
    public static int page = 0;
    private static ArrayList<Bitmap> list = new ArrayList<>();
    private static ArrayList<String> comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_main);

        // mBaaS からコメントを取得
        comment = CommentGenerator.createCommentData(getApplication());

        // ループ回数10(要検討)
        for (int id = 0; id <= 11; id++) {
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
                    BitmapAdapter adapter = new BitmapAdapter(getApplicationContext(), R.layout.image_griditem, list);
                    GridView gridView = (GridView) findViewById(R.id.gridView);
                    gridView.setAdapter(adapter);
                }
            }.execute("https://mb.api.cloud.nifty.com/2013-09-01/applications/xenlIaKJArb0UrG/publicFiles/picture" + id + ".jpg");
        }

        GridView gridView = (GridView) findViewById(R.id.gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //画像が選ばれたときの処理
                Intent intent = new Intent(getApplication(), ImageSubActivity.class);
                intent.putExtra("PageNumber", position);
                intent.putStringArrayListExtra("Comment", comment);
                startActivity(intent);
            }
        });
    }

    // ArrayList<Bitmap> をインテントで渡せなかったため作成
    protected static ArrayList<Bitmap> getList(){
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setSelection(page);
    }

    //戻るアニメーション設定
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            finish();
            overridePendingTransition(R.animator.page_in_right, R.animator.page_out_left);
        }
        return true;
    }
}
