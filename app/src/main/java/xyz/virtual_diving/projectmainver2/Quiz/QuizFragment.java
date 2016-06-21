package xyz.virtual_diving.projectmainver2.Quiz;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import xyz.virtual_diving.projectmainver2.R;

/**
 * Created by b1014100 on 2016/06/21.
 */
public class QuizFragment extends Fragment {
    int position ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizactivity_fragment, container, false);

        //Bundleからページ数をもらってくる
        Bundle args = getArguments();
        position = args.getInt("page",0);

        //ページ数の表示
        TextView pageTextView = (TextView)view.findViewById(R.id.quiz_textView);
        pageTextView.setText("page"+position);

        /*ボタンで遷移するところの実装*/
        //ボタンのID取得
        Button leftAllow = (Button)view.findViewById(R.id.quiz_button1);
        Button rightAllow = (Button)view.findViewById(R.id.quiz_button2);
        //クリックリスナ
        leftAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(-1);
            }
        });
        rightAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(1);
            }
        });


        return view;
    }
    //ボタンを押したときにviewpagerの移動
    //iは、1で進む、-1で戻る
    private void moveViewPager(int i){
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.quiz_viewpager);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + i);
    }
}
