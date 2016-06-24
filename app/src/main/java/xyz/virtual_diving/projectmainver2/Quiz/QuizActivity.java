package xyz.virtual_diving.projectmainver2.Quiz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import xyz.virtual_diving.projectmainver2.R;

public class QuizActivity extends AppCompatActivity implements QuizResultFragment.QuizActivityFragmentListener{

    public ArrayList<QuizDetail> quizDetails;
    //正解数
    public static int ans;
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            String TAG = "tag";
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: ");
            }
            @Override
            public void onPageSelected(int position) {
                Log.d("", "onPageSelected: " + "セレクト");
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: ");
            }
        });

        viewPager.setAdapter(adapter);
    }

    //QuizDetailに内容をセットする
    private void setQuizDetails() {
        quizDetails = new ArrayList<QuizDetail>();
        ans = 0;
        for (int i = 0; i < 4; i++) {
            QuizDetail quizDetail = new QuizDetail();
            quizDetail.setChoices(new String[]{"1", "2", "3"});
            quizDetail.setContent("魚を探せ");
            quizDetail.setImageUrl(R.drawable.zukanlist_sakana0);
            quizDetail.shuffleChoices();
            quizDetails.add(quizDetail);
        }
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public  void pulsAns(){
        this.ans++;
    }

    public void quit(){
//        Intent intent = new Intent(getApplication(), MainActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onQuizFragmentEvent1() {
        finish();
//        Intent intent = new Intent(getApplication(), MainActivity.class);
//        startActivity(intent);
    }
}