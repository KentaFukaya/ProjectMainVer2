package xyz.virtual_diving.projectmainver2.Quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import xyz.virtual_diving.projectmainver2.DB.QuizDatabase;
import xyz.virtual_diving.projectmainver2.R;

public class QuizActivity extends AppCompatActivity implements QuizResultFragment.QuizActivityFragmentListener{

    public ArrayList<QuizDetail> quizDetails;
    //正解数
    private static int ans;
    //QuizSQLiteOpenHelperで使う
    private static Context ctx;
    private int fishIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizactivity_main);
        Intent intent = getIntent();
        fishIcon = intent.getIntExtra("fishIcon", 0);
        ctx = this;
        ans = 0;

        setQuizDetails();
        setViews();
    }

    //QuizSQLiteOpenHelperで使う
    public static Context getContext() {
        return ctx;
    }

    //viewpagerのセット
    private void setViews() {
        FragmentManager manager = getSupportFragmentManager();
        QuizViewPager viewPager = (QuizViewPager) findViewById(R.id.quiz_viewpager);
        final QuizFragmentPagerAdapter adapter = new QuizFragmentPagerAdapter(manager, quizDetails, fishIcon);

        if (viewPager != null) {
            viewPager.setAdapter(adapter);
        }
    }

    //QuizDetailに内容をセットする
    private void setQuizDetails() {
        ArrayList<QuizDetail> quizDetails = new ArrayList<>();
        QuizDetail quizDetail;
        //1
        quizDetail = new QuizDetail();
        quizDetail.setId(1);
        quizDetail.setImageUrl(R.drawable.zukanlist_sakana0);
        quizDetail.setFishId(1);
        quizDetail.setQuestion("ヤリイカの他の呼び方があります。それは何でしょう？");
        quizDetail.setChoices(new String[]{"シャクハチイカ", "シャクキュウイカ", "シャクジュウイカ"});
        quizDetails.add(quizDetail);
        //2
        quizDetail = new QuizDetail();
        quizDetail.setId(2);
        quizDetail.setImageUrl(R.drawable.zukanlist_sakana0);
        quizDetail.setFishId(0);
        quizDetail.setQuestion("マアジの旬はいつでしょう？");
        quizDetail.setChoices(new String[]{"夏", "春", "冬"});
        quizDetails.add(quizDetail);
        //3
        quizDetail = new QuizDetail();
        quizDetail.setId(3);
        quizDetail.setImageUrl(R.drawable.zukanlist_sakana0);
        quizDetail.setFishId(2);
        quizDetail.setQuestion("クロマグロは何科の魚でしょう？");
        quizDetail.setChoices(new String[]{"サバ科", "アジ科", "タイ科"});
        quizDetails.add(quizDetail);
        //4
        quizDetail = new QuizDetail();
        quizDetail.setId(4);
        quizDetail.setImageUrl(R.drawable.zukanlist_sakana0);
        quizDetail.setFishId(3);
        quizDetail.setQuestion("マダイの大きさは約何cmでしょう？");
        quizDetail.setChoices(new String[]{"120cm", "40cm", "80cm"});
        quizDetails.add(quizDetail);

        for (int i = 0; i < quizDetails.size(); i++) {
            Log.d("ddd", "setQuizDetails: " + i);
            //データベースに入れます
            QuizDatabase.setQuizData(quizDetails.get(i));
        }
        //データベースから取得
//        this.quizDetails = QuizDatabase.getQuizDetailsAll();
        this.quizDetails = QuizDatabase.getQuizDetails(fishIcon);

    }

    public static int getAns() {
        return ans;
    }

    private static void setAns(int setAns) {
        ans = setAns;
    }

    public static void plusAns(){
        setAns(getAns() + 1);
    }

    @Override
    public void onQuizFragmentEvent1() {
        finish();
    }
}