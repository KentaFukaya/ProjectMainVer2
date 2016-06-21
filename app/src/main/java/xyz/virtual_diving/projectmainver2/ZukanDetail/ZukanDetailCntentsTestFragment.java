package xyz.virtual_diving.projectmainver2.ZukanDetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.virtual_diving.projectmainver2.R;

public class ZukanDetailCntentsTestFragment extends Fragment {
    int position = 0;
    String[] contents;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       //ページ数、説明ぶんの取得
        View view = inflater.inflate(R.layout.fragment_zukan_detail_cntents_test, container, false);

        Bundle args = getArguments();
        position = args.getInt("page",0);
        contents = ZukanDetailMain.zukanDetail.getContents();


        //説明文の表示
        TextView pageContents = (TextView)view.findViewById(R.id.ZukanDetail_Contents);
        pageContents.setText(contents[position]);

        //矢印の表示
        //←はpageが2の解き表示されない。
        //→はpageが0のとき表示されない
        ImageView leftAllow = (ImageView)view.findViewById(R.id.ZukanDetail_Left);
        if(position == 0)  leftAllow.setVisibility(leftAllow.INVISIBLE);
        else leftAllow.setVisibility(leftAllow.VISIBLE);
        ImageView RightAllow = (ImageView)view.findViewById(R.id.ZukanDetail_Right);
        if(position == contents.length -1)  RightAllow.setVisibility(RightAllow.INVISIBLE);
        else RightAllow.setVisibility(RightAllow.VISIBLE);

        //bottonのクリックリスナー
        leftAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(-1);
            }
        });
        RightAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(1);
            }
        });

        //ページ数の表示
        TextView pagecount = (TextView)view.findViewById(R.id.ZukanDetail_PageCount);
        pagecount.setText(position+1+"/3");

        return view;
    }

    //ボタンを押したときにviewpagerの移動
    //iは、1で進む、-1で戻る
    private void moveViewPager(int i){
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.ZukanDetail_ViewPager);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + i);
    }
}
