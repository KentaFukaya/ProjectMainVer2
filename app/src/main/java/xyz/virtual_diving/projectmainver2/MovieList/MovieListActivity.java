package xyz.virtual_diving.projectmainver2.MovieList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import xyz.virtual_diving.projectmainver2.DB.VideoListDatabase;
import xyz.virtual_diving.projectmainver2.MovieList.compareato.IdComparator;
import xyz.virtual_diving.projectmainver2.MovieList.compareato.ViewCountComparator;
import xyz.virtual_diving.projectmainver2.R;

public class MovieListActivity extends AppCompatActivity {
    ViewPager viewPager;
    public static List<ListItem> Movielist = new ArrayList<ListItem>();
    public static List<ListItem> list0 = new ArrayList<ListItem>();
    public static List<ListItem> list1 = new ArrayList<ListItem>();
    private static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movielist_main);
        ctx = this;
        //listの初期化
        Movielist = new ArrayList<ListItem>();
        list0 = new ArrayList<ListItem>();
        list1 = new ArrayList<ListItem>();

        setItem();//listにでーたを　積める

        //listを並び替え
        Collections.sort(list0, new IdComparator());
        Collections.sort(list1, new ViewCountComparator());

        viewPager = (ViewPager) findViewById(R.id.MovieList_pager);
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager()));
    }

    public static Context getContext() {
        return ctx;
    }

    /*
       *db テスト用
       */
    void setItem() {
        for (int i = 0; i < 20; i++) {
            Random r = new Random();
            VideoListDatabase.setData(i, "title" + i, "contents" + i, i, r.nextInt(10000), "url" + i);
        }
        VideoListDatabase.getAllData(Movielist);
        VideoListDatabase.getAllData(list1);
        VideoListDatabase.getAllData(list0);
    }
    //テスト用
    /*public void setItem(){
        for(int i = 0 ; i < 10 ; i++) {
            //randomクラスの作成
            Random r = new Random();
            ListItem item = new ListItem();
            //String "sample"をR.drawable.sampleの番地に変換
            String nameString = "image1";
            // int name = getResources().getIdentifier(nameString,"drawable",getActivity().getPackageName());
            int name = R.drawable.image1;
            int id = r.nextInt(30);
            item.setListId(id);
            item.setTitleText("タイトル" + i);
            item.setContentsText("本文：id=" + id);
            item.setImageId(name);
            item.setMovieURL("Movie URL :" + id);
            item.setViewCount(r.nextInt(10000));
            Movielist.add(item);
            list0.add(item);
            list1.add(item);
        }
    }*/
}