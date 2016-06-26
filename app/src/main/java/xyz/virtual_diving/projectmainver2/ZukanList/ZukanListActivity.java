package xyz.virtual_diving.projectmainver2.ZukanList;



import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import xyz.virtual_diving.projectmainver2.DB.ZukanDatabase;
import xyz.virtual_diving.projectmainver2.Image.ImageActivity;
import xyz.virtual_diving.projectmainver2.MovieList.MovieListActivity;
import xyz.virtual_diving.projectmainver2.R;


public class ZukanListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static ArrayList<ZukanListItem> Zukanitems = new ArrayList<>();
    private static Context ctx;
    private String ZukanFishName[] = new String [4];
    private String ZukanAbstract[] = new String [4];
    private String ZukanContents[] [] = new String[4][3];
    /*-----------------------------------*/
    private AlertDialog mActions;
    public String[] AlertContents = new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zukanlist_main);
        ctx = this;

        //help画面の作成
        makeAlertDialog();
        //navgaitonbarの作成
        makeNavigationBar();

        setItemsfromDB();
        getItemsformDB();//Itemsの内容のセット

        //ViewPagerのセット
        FragmentManager manager = getSupportFragmentManager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.zukanViewpager);
        ZukanFragmentStatePagerAdapter adapter = new ZukanFragmentStatePagerAdapter(manager);
        viewPager.setAdapter(adapter);
    }
    public static Context getContext() {
        return ctx;
    }

    //データベースに追加するための配列のデータを入れる
    public  void setZukanContents(){
        //page0
        ZukanFishName[0] = "マアジ";
        ZukanAbstract[0] = "スズキ目アジ科";
        ZukanContents[0] [0] = "北西太平洋の沿岸部に分布する海水魚。大きさは大体50cm。";
        ZukanContents[0] [1] = "日本では良く食卓に並ぶ。さて、味はいかほどか・・・\nどの季節でも獲れるが、旬は夏。";
        ZukanContents[0] [2] = "代表的な調理方法は\n 唐揚げ、フライ、ムニエル、刺身など。\n 日本各地にアジを使った郷土料理があるよ。";

        //page1
        ZukanFishName[1] = "ヤリイカ";
        ZukanAbstract[1] = "閉眼目ヤリイカ科\n";
        ZukanContents[1] [0] = "北海道から九州まで日本列島沿海に分布。\n大きさは大体20〜40cmくらい。";
        ZukanContents[1] [1] = "函館での旬は3月〜5月。 名前の由来は姿形が槍の穂に似ているから。 \n他にも「ササイカ」「シャクハチイカ」など呼び名がある。";
        ZukanContents[1] [2] = "代表的な調理方法は\n刺身、寿司、直火焼き、塩辛など。\n函館なら朝市でよく見れるかも。";

        //page2
        ZukanFishName[2] = "クロマグロ";
        ZukanAbstract[2] = "スズキ目サバ科\n";
        ZukanContents[2] [0] = "日本海側や来た太平洋側に分布。\n大きさは全長3m、体重400kgを超えるものも。";
        ZukanContents[2] [1] = "どの季節でも旬のものが食べられるが、\n基本的には春から夏。\n地方によって「ホンマグロ」、「クロシビ」など呼び名がある。";
        ZukanContents[2] [2] = "代表的な調理方法は\n刺身、寿司、ステーキ、缶詰など幅広い。\n日本ではかなり昔から食べられている。";

        //page2
        ZukanFishName[3] = "マダイ";
        ZukanAbstract[3] = "スズキ目タイ科\n";
        ZukanContents[3] [0] = "日本列島全域に分布。\n大きさは120cmと比較的大きい。";
        ZukanContents[3] [1] = "旬は1〜3月ごろ。\n頑丈な顎と歯でエビやカニなど固い殻でも噛み砕いて食べる。";
        ZukanContents[3] [2] = "代表的な調理方法は\n刺身、焼き魚、吸い物、煮付けなど多種多様。\n日本では昔から目出度い魚だとされている。";

    }
    //データベースに追加する
    public void setItemsfromDB(){
        setZukanContents();

        String contents[] = new String[3];
        contents[0] = "０番目の詳しい内容\n関係ないけどスワイプもボタンタッチでも次のページに行けるよ\n左の矢印は消してるよ";
        contents[1] = "1番目の詳しい内容\n魚に詳しくないので名に書いたらいいかわかりませんうまくいくといいな";
        contents[2] = "2番目の詳しい内容\n最後のぺーじ、どんなクイズができているんでしょう？\n右の矢印は消してるよ";
        for(int i = 0 ; i < ZukanFishName.length ; i++) {
            ZukanDatabase.setZukanData(i,i,ZukanFishName[i],ZukanAbstract[i],ZukanContents[i]);
        }

    }
    //データベースの情報をとってくる。
    public void getItemsformDB(){
        Zukanitems = new ArrayList<>();//ArrayListの初期化
        ZukanDatabase.getAllImageUrlandFishName(Zukanitems);
    }
    /*
    *Itemsのセット用のメソッド
    */
    /*public void setItems() {

        Zukanitems = new ArrayList<ZukanListItem>();//ArrayListの初期化

        for (int i = 0; i < 13; i++) {//13=魚の数
            ZukanListItem Zukanitem = new ZukanListItem();

            if (i % 3 == 0) {//画像入れてるだけ
                Zukanitem.setIcon(R.drawable.sakana);
            } else if (i % 3 == 1) {
                Zukanitem.setIcon(R.drawable.kurione);
            } else {
                Zukanitem.setIcon(R.drawable.kurage);
            }

            Zukanitem.setTitle("タイトル" + i);//引っ張ってくる
            Zukanitem.setDe("説明" + i);
            Zukanitem.setCo(i * 100 + "回");
            Zukanitems.add(Zukanitem);
        }//構成OK
    }*/

    /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^navigationbarkannren ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
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
        navigationView.setCheckedItem(R.id.nav_gallery);
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
            // Handle the camera action

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, MovieListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(this, ImageActivity.class);
            startActivity(intent);
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
}
