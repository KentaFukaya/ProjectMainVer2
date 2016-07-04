package xyz.virtual_diving.projectmainver2.MovieList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import xyz.virtual_diving.projectmainver2.DB.VideoListDatabase;
import xyz.virtual_diving.projectmainver2.Image.ImageActivity;
import xyz.virtual_diving.projectmainver2.MovieList.compareato.IdComparator;
import xyz.virtual_diving.projectmainver2.MovieList.compareato.ViewCountComparator;
import xyz.virtual_diving.projectmainver2.R;
import xyz.virtual_diving.projectmainver2.ZukanList.ZukanListActivity;

public class MovieListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ViewPager viewPager;
    public static List<ListItem> Movielist = new ArrayList<ListItem>();
    public static List<ListItem> list0 = new ArrayList<ListItem>();
    public static List<ListItem> list1 = new ArrayList<ListItem>();
    private static Context ctx;

    private AlertDialog mActions;
    public String[] AlertContents = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movielist_main);
        //help画面の作成
        makeAlertDialog();
        //navgaitonbarの作成
        makeNavigationBar();


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

    /*----------------------------navigationbar関連-----------------------------*/

    //navigation bar の作成
    private void makeNavigationBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //タイトルの表示
        getSupportActionBar().setTitle("");

        //←のメニューの表示
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        //左のメニュの内部の判定
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_slideshow);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, ZukanListActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.page_in_up, R.animator.page_out_down);
            // Handle the camera action
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(this, ImageActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.page_in_left, R.animator.page_out_right);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //Alertdialogの作成(Helpの画面)
    public void makeAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog));
        setAlertContents();
        builder.setTitle(AlertContents[0]).setMessage(AlertContents[1]).setPositiveButton("OK", null);
        mActions=builder.create();
    }
    //AlertDiaogの内容を詰める
    private void setAlertContents() {
        AlertContents[0] = "title" ;
        AlertContents[1] = "Contents:helpの内容を書きたい";
    }

    /*helpbottonの表示
    *http://techbooster.jpn.org/andriod/ui/3383/
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューの要素を追加して取得
        MenuItem actionItem = menu.add("Help Button");
        // SHOW_AS_ACTION_IF_ROOM:余裕があれば表示
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        // アイコンを設定
        actionItem.setIcon(R.drawable.appbar_ic_action_help);
        return true;
    }

    /*
    *helpBouttonのクリック判定
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        mActions.show();
        return true;
    }

    //戻るアニメーション設定
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            finish();
            overridePendingTransition(R.animator.page_in_up, R.animator.page_out_down);
        }
        return true;
    }
}