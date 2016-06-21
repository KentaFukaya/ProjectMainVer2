package xyz.virtual_diving.projectmainver2.Quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by b1014100 on 2016/06/13.
 */
public class QuizFragmentPagerAdapter extends FragmentPagerAdapter {

    public QuizFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // Bundleに位置情報,説明をセット
        Bundle bundle = new Bundle();
        bundle.putInt("page", position);

        // Fragment をつくり Bundle をセットする
        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
