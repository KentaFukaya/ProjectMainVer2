package xyz.virtual_diving.projectmainver2.ZukanList;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import xyz.virtual_diving.projectmainver2.DB.ZukanDatabase;
import xyz.virtual_diving.projectmainver2.R;


public class ZukanListActivity extends AppCompatActivity {
    public static ArrayList<ZukanListItem> Zukanitems = new ArrayList<>();
    private static Context ctx;
    private String ZukanFishName[] = new String [4];
    private String ZukanAbstract[] = new String [4];
    private String ZukanContents[] [] = new String[4][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zukanlist_main);
        ctx = this;

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
}
