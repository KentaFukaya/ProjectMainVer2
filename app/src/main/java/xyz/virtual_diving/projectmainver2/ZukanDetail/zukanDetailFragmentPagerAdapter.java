package xyz.virtual_diving.projectmainver2.ZukanDetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by b1014100 on 2016/06/13.
 */
public class zukanDetailFragmentPagerAdapter extends FragmentPagerAdapter {
   public static ZukanDetail zukanDetail = ZukanDetaiActivity.zukanDetail;

    public zukanDetailFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // Bundleに位置情報,説明をセット
        Bundle bundle = new Bundle();
        bundle.putInt("page", position);

        // Fragment をつくり Bundle をセットする
        ZukanDetailFragment fragment = new ZukanDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
