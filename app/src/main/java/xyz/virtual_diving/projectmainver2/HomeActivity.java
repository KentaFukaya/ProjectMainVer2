package xyz.virtual_diving.projectmainver2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import xyz.virtual_diving.projectmainver2.Image.ImageActivity;
import xyz.virtual_diving.projectmainver2.MovieList.MovieListActivity;
import xyz.virtual_diving.projectmainver2.ZukanList.ZukanListActivity;


public class HomeActivity extends Activity {
    private ImageButton DM_btm, FG_btm;
    private BootstrapButton Image_btm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.homeactivity_main);

        DM_btm = (ImageButton)findViewById(R.id.VDButton); //ダイビングアクティビティに飛ぶボタンのidを渡す
        DM_btm.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(HomeActivity.this, MovieListActivity.class); //ダイビングアクティビティに飛ぶ処理
                startActivity(intent);
                //テストコメント
            }
        });

        FG_btm = (ImageButton)findViewById(R.id.FGButton); //図鑑アクティビティ用
        FG_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ZukanListActivity.class); //図鑑アクティビティにに飛ぶ処理
                startActivity(intent);
            }
        });

        Image_btm = (BootstrapButton)findViewById(R.id.Button); //スクリーンショット共有アクティビティ用(仮)
        Image_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ImageActivity.class); //アクティビティにに飛ぶ処理
                startActivity(intent);
            }
        });


    }
}
