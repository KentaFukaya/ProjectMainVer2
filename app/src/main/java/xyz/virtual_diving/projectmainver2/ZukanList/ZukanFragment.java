package xyz.virtual_diving.projectmainver2.ZukanList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import xyz.virtual_diving.projectmainver2.R;
import xyz.virtual_diving.projectmainver2.ZukanDetail.ZukanDetaiActivity;

/**
 * Created by b1014248 on 2016/06/17.
 */
public class ZukanFragment extends Fragment {
    ArrayList<ZukanListItem> items;
    ZukanListItem item;
    int pos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.zukanlist_fragmnet, null);
        /*ページもってくるとして
        　page=0 item(0)1.2
          page=1 item 3.4.5
          だからpage*3=posをつかう
         */

        Bundle bundle = getArguments();
        int i = bundle.getInt("page", 0);
        pos = i*3;
        items = ZukanListActivity.Zukanitems;


        //1個目のコンテンツセット
        item = items.get(pos++);
        ImageView image1 = (ImageView) layout.findViewById(R.id.image1);
        TextView title1 = (TextView) layout.findViewById(R.id.title1);
        image1.setImageResource(R.drawable.zukanlist_sakana0+item.getIcon());
        title1.setText(item.getT());

        //2個目のコンテンツセット
        if (items.size() > pos) {//あったら処理
            item = items.get(pos++);
            ImageView image2 = (ImageView) layout.findViewById(R.id.image2);
            TextView title2 = (TextView) layout.findViewById(R.id.title2);
            image2.setImageResource(R.drawable.zukanlist_sakana0+item.getIcon());
            title2.setText(item.getT());
        }
        //3個目のコンテンツセット
        if (items.size() > pos) {
            item = items.get(pos++);
            ImageView image3 = (ImageView) layout.findViewById(R.id.image3);
            TextView title3 = (TextView) layout.findViewById(R.id.title3);
            image3.setImageResource(R.drawable.zukanlist_sakana0+item.getIcon());
            title3.setText(item.getT());
        }

        pos = i*3;//posの初期化
        //クリックリスナー
        LinearLayout ZukanListItem1 = (LinearLayout)layout.findViewById(R.id.ZukanListItme1);
        ZukanListItem1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Log.d("Test", "onClick: ZukanItem1 pos = "+pos);
                Intent intent = new Intent(getActivity(),ZukanDetaiActivity.class); //図鑑アクティビティにに飛ぶ処理
                intent.putExtra("id",pos);
                startActivity(intent);
            }
        });
        LinearLayout ZukanListItem2 = (LinearLayout)layout.findViewById(R.id.ZukanListItme2);
        ZukanListItem2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(getActivity(),ZukanDetaiActivity.class); //図鑑アクティビティにに飛ぶ処理
                intent.putExtra("id",pos+1);
                startActivity(intent);
                Log.d("Test", "onClick: ZukanItem2 pos = "+pos+1);
            }
        });
        LinearLayout ZukanListItem3 = (LinearLayout)layout.findViewById(R.id.ZukanListItme3);
        ZukanListItem3.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(getActivity(),ZukanDetaiActivity.class); //図鑑アクティビティにに飛ぶ処理
                intent.putExtra("id",pos+2);
                startActivity(intent);
                Log.d("Test", "onClick: ZukanItem3 pos = "+pos+2);
            }
        });

        return layout;
    }
}
