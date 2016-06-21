package xyz.virtual_diving.projectmainver2.MovieList;

/**
 * Created by b1014100 on 2016/06/06.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;


public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    public MyFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page", i);
        fragment.setArguments(bundle);
        return  fragment;
    }
    @Override
    public int getCount() { return 2; }
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0: return "SortById";
            default:return "SortByViewCount";
        }
    }

}