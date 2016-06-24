package xyz.virtual_diving.projectmainver2.Quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by b1014100 on 2016/06/13.
 */
public class QuizFragmentPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<QuizDetail> quizDetails;

    public QuizFragmentPagerAdapter(FragmentManager fm, ArrayList<QuizDetail> quizDetails) {
        super(fm);
        this.quizDetails = quizDetails;
        //rawContents = choices;
    }
    @Override
    public Fragment getItem(int position) {
        Log.d("", "getItem: "+position +"ans"+QuizActivity.ans);
        //クイズ画面
        if(position < quizDetails.size()) {
            // Bundleに位置情報,説明をセット
            Bundle bundle = new Bundle();
            bundle.putInt("page", position);
            bundle.putSerializable("quizDetail", quizDetails.get(position));
            // Fragment をつくり Bundle をセットする
            QuizFragment fragment = new QuizFragment();
            fragment.setArguments(bundle);
            return fragment;
         //クイズ結果前画面
        }else if(position == quizDetails.size()){
            Bundle bundle = new Bundle();
            bundle.putInt("page", position);
            QuizPreResultFragment fragment = new QuizPreResultFragment();
            fragment.setArguments(bundle);
            return fragment;
         //クイズ結果画面
        }else {
            Bundle bundle = new Bundle();
            bundle.putInt("page", position);
            QuizResultFragment fragment = new QuizResultFragment();
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    @Override
    public int getCount() {
        return quizDetails.size() + 2;
    }
}
