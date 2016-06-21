package xyz.virtual_diving.projectmainver2.ZukanList;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import xyz.virtual_diving.projectmainver2.DB.ZukanDatabase;
import xyz.virtual_diving.projectmainver2.R;



public class ZukanListActivity extends AppCompatActivity {
    public static ArrayList<ZukanListItem> Zukanitems = new ArrayList<>();
    private static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zukanlist_main);
        ctx = this;

        //ViewPagerのセット
        FragmentManager manager = getSupportFragmentManager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.zukanViewpager);
        ZukanFragmentStatePagerAdapter adapter = new ZukanFragmentStatePagerAdapter(manager);
        viewPager.setAdapter(adapter);

        setItemsfromDB();
        getItemsformDB();//Itemsの内容のセット


    }
    public static Context getContext() {
        return ctx;
    }

    //データベースに追加する
    public void setItemsfromDB(){
        String contents[] = new String[3];
        contents[0] = "０番目の詳しい内容\n関係ないけどスワイプもボタンタッチでも次のページに行けるよ\n左の矢印は消してるよ";
        contents[1] = "1番目の詳しい内容\n魚に詳しくないので名に書いたらいいかわかりませんうまくいくといいな";
        contents[2] = "2番目の詳しい内容\n最後のぺーじ、どんなクイズができているんでしょう？\n右の矢印は消してるよ";
        for(int i = 0 ; i < 13 ; i++) {
            if (i % 3 == 0) {//画像入れてるだけ
                ZukanDatabase.setZukanData(i,i%3,"魚", "魚の説明　id ="+i,contents);
            } else if (i % 3 == 1) {
                ZukanDatabase.setZukanData(i,i%3,"クリオネ", "魚の説明　id ="+i,contents);
            } else {
                ZukanDatabase.setZukanData(i,i%3,"クラゲ", "魚の説明　id ="+i,contents);
            }
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
    /*
    public void setItems() {

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
}
