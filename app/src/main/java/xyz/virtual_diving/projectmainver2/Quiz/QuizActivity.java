package xyz.virtual_diving.projectmainver2.Quiz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import xyz.virtual_diving.projectmainver2.DB.QuizDatabase;
import xyz.virtual_diving.projectmainver2.R;

public class QuizActivity extends AppCompatActivity implements QuizResultFragment.QuizActivityFragmentListener{

    public ArrayList<QuizDetail> quizDetails;
    //正解数
    private static int ans;
    //QuizSQLiteOpenHelperで使う
    private static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizactivity_main);
        ctx = this;

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
        final QuizFragmentPagerAdapter adapter = new QuizFragmentPagerAdapter(manager, quizDetails);

        if (viewPager != null) {
            viewPager.setAdapter(adapter);
        }
    }

    //QuizDetailに内容をセットする
    private void setQuizDetails() {
        ans = 0;
        for (int i = 0; i < 3; i++) {
            QuizDetail quizDetail = new QuizDetail();
            quizDetail.setId(i);
            quizDetail.setImageUrl(R.drawable.zukanlist_sakana0);
            quizDetail.setFishId(0);
            quizDetail.setQuestion("魚を探せ");
            quizDetail.setChoices(new String[]{"1", "2", "3"});
            //データベースに入れます
            QuizDatabase.setQuizData(quizDetail);
        }
        //データベースから取得
        quizDetails = QuizDatabase.getQuizDetailsAll();
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