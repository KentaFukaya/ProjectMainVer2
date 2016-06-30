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
        quizDetail.setImageUrl(R.drawable.zukanlist_sakana0 + fishIcon);
        quizDetail.setFishId(0);
        quizDetail.setQuestion("ウロコから数えた魚の寿命、１番長生きなのは？");
        quizDetail.setChoices(new String[]{"コイ", "ヒラメ", "タラ"});
        quizDetails.add(quizDetail);
        //2
        quizDetail = new QuizDetail();
        quizDetail.setId(2);
        quizDetail.setImageUrl(R.drawable.zukanlist_sakana0 + fishIcon);
        quizDetail.setFishId(0);
        quizDetail.setQuestion("魚の血は何色？");
        quizDetail.setChoices(new String[]{"赤色", "青色", "緑色"});
        quizDetails.add(quizDetail);
        //3
        quizDetail = new QuizDetail();
        quizDetail.setId(3);
        quizDetail.setImageUrl(R.drawable.zukanlist_sakana0 + fishIcon);
        quizDetail.setFishId(0);
        quizDetail.setQuestion("海で生まれて、川で育つ魚は？");
        quizDetail.setChoices(new String[]{"ウナギ", "アユ", "サケ"});
        quizDetails.add(quizDetail);

        for (int i = 0; i < quizDetails.size(); i++) {
            Log.d("ddd", "setQuizDetails: " + i);
            //データベースに入れます
            QuizDatabase.setQuizData(quizDetails.get(i));
        }
        //データベースから取得
        this.quizDetails = QuizDatabase.getQuizDetailsAll();
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