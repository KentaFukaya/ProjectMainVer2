package xyz.virtual_diving.projectmainver2.ZukanDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.virtual_diving.projectmainver2.DB.ZukanDatabase;
import xyz.virtual_diving.projectmainver2.Quiz.QuizActivity;
import xyz.virtual_diving.projectmainver2.R;

public class ZukanDetailMain extends AppCompatActivity {
    private ImageButton backButton,quizButton;
    public static ZukanDetail zukanDetail;
    private static Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zukan_detail_main);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);

        ctx = this;
       //setZukanDetail();
        setZukanDetailFromDB(id);
        setALL();
        //pageVIewのセット
        setViews();
        //戻るボタンのクリックリスナー
        backButton = (ImageButton) findViewById(R.id.ZukanDetail_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Zukan_detail", "onClick: backButton");
                finish();
            }
        });
        //quizボタンのクリックリスナー
        quizButton = (ImageButton) findViewById(R.id.ZukanDetail_quiz);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),QuizActivity.class); //図鑑アクティビティにに飛ぶ処理
                startActivity(intent);
                Log.d("Zukan_detail", "onClick: QuziButton");
            }
        });
    }
    public static Context getContext() {
        return ctx;
    }

    //viewpagerのセット
    private void setViews(){
        FragmentManager manager = getSupportFragmentManager();
        ViewPager viewPager = (ViewPager)findViewById(R.id.ZukanDetail_ViewPager);
        zukanDetailFragmentPagerAdapter adapter = new zukanDetailFragmentPagerAdapter(manager);
        viewPager.setAdapter(adapter);
    }
    //データベースからzukanDetailをひぱってくる
    private void setZukanDetailFromDB(int id) {
        zukanDetail = new ZukanDetail();
        ZukanDatabase.getAllDatabyId(zukanDetail,id);
    }
    //Zukandetailに内容をセットする
    /*private void setZukanDetail(){
        zukanDetail = new ZukanDetail();
        zukanDetail.setFishName("くらえげ");
        zukanDetail.setImageUrl(R.drawable.fish);
        zukanDetail.setAbstract("このさかな　こんな感じです的な説明を書きたい。\nできればデータベースからとってきたい（SQLITEのほう）");
        String contents[] = new String[3];
        contents[0] = "０番目の詳しい内容\n関係ないけどスワイプもボタンタッチでも次のページに行けるよ\n左の矢印は消してるよ";
        contents[1] = "1番目の詳しい内容\n魚に詳しくないので名に書いたらいいかわかりませんうまくいくといいな";
        contents[2] = "2番目の詳しい内容\n最後のぺーじ、どんなクイズができているんでしょう？\n右の矢印は消してるよ";
        zukanDetail.setContents(contents);
    }*/
    //zukandeitalの表示
    private void setALL(){
        //魚の画像の表示
        ImageView imageView = (ImageView) findViewById(R.id.ZukanDetail_pic);
        imageView.setImageResource(R.drawable.sakana0+ zukanDetail.getImageUrl());
        //魚の名前の表示
        TextView pageFishName = (TextView)findViewById(R.id.ZukanDetail_fishName);
        pageFishName.setText(zukanDetail.getFishName());
        //魚の概要の表示
        TextView abstractText = (TextView) findViewById(R.id.ZukanDetail_abstarct);
        abstractText.setText(zukanDetail.getAbstract());
    }
}
