package xyz.virtual_diving.projectmainver2.ZukanList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

//import android.support.v4.app.FragmentTransaction;

/**
 * Created by b1014248 on 2016/06/17.
 */
public class ZukanFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ZukanAdapterItem> Zukanitems = ZukanListActivity.Zukanitems;

    public ZukanFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        //page数をBundleに詰める
        Fragment fragment = new ZukanFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page", i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return getPageSize();
    }

    //ページ数を動的に指定するためのメソッド
    public int getPageSize() {
        int pageSize = Zukanitems.size();//zukanitemの総数を取得
        /*
        * ex) pageSize == 12 の場合　12 / 3 == 4を返す
        * 　　pageSize == 13 or 14　の場合　13 / 3 + 1= 5 を返す
        */
        if (Zukanitems.size() % 3 == 0) return pageSize / 3;
        else return pageSize / 3 + 1;
    }
}
