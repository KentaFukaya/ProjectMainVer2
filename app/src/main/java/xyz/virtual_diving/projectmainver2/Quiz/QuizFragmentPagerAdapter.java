package xyz.virtual_diving.projectmainver2.Quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

public class QuizFragmentPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<QuizDetail> quizDetails;
    int fishIcon;

    public QuizFragmentPagerAdapter(FragmentManager fm, ArrayList<QuizDetail> quizDetails, int fishIcon) {
        super(fm);
        this.quizDetails = quizDetails;
        this.fishIcon = fishIcon;
        //rawContents = choices;
    }
    @Override
    public Fragment getItem(int position) {
        Log.d("", "getItem: "+position +"ans"+QuizActivity.getAns());
        //クイズ画面
        if(position < quizDetails.size()) {
            // Bundleに位置情報,説明をセット
            Bundle bundle = new Bundle();
            bundle.putInt("page", position);
            bundle.putSerializable("quizDetail", quizDetails.get(position));
            bundle.putInt("fishIcon", fishIcon);
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
