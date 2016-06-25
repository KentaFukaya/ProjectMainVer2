package xyz.virtual_diving.projectmainver2.Quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.virtual_diving.projectmainver2.R;

public class QuizFragment extends Fragment {
    int position = 0;
    QuizDetail quizDetail;
    ImageView maru_img;
    ImageView batu_img;
    AlphaAnimation maruAnime;
    AlphaAnimation batuAnime;
    Button choice1;
    Button choice2;
    Button choice3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //ページ数、説明ぶんの取得
        View view = inflater.inflate(R.layout.quizactivity_fragment_quiz, container, false);
        Bundle args = getArguments();
        position = args.getInt("page");
        quizDetail = (QuizDetail) args.getSerializable("quizDetail");

        //説明文の表示
        TextView pageContents = (TextView) view.findViewById(R.id.quizContent);
        pageContents.setText(quizDetail.getQuestion());

        //問題番号表示
        TextView quizpage = (TextView) view.findViewById(R.id.quizpage);
        quizpage.setText("第" + (position + 1) + "問 ");

        ImageView imageView = (ImageView) view.findViewById(R.id.QuizDetail_pic);
        imageView.setImageResource(quizDetail.getImageUrl());

        /*ボタンで遷移の実装*/
        choice1 = (Button) view.findViewById(R.id.choice1);
        choice1.setText(quizDetail.getChoices()[0]);
        choice1.setOnClickListener(choiceButtonClickListener);

        choice2 = (Button) view.findViewById(R.id.choice2);
        choice2.setText(quizDetail.getChoices()[1]);
        choice2.setOnClickListener(choiceButtonClickListener);

        choice3 = (Button) view.findViewById(R.id.choice3);
        choice3.setText(quizDetail.getChoices()[2]);
        choice3.setOnClickListener(choiceButtonClickListener);

        //アニメーションセット
        setAnime(view);

        //正解　画像セット
        maru_img = (ImageView) view.findViewById(R.id.maruimg);
        maru_img.setVisibility(View.GONE);
        maru_img.setImageResource(R.drawable.quizactivity_maru);
        //不正解　画像セット
        batu_img = (ImageView) view.findViewById(R.id.batsuimg);
        batu_img.setVisibility(View.GONE);
        batu_img.setImageResource(R.drawable.quizactivity_batsu);

        return view;
    }

    //ボタンを押したときにviewpagerの移動
    //iは、1で進む、-1で戻る
    private void moveViewPager(int i) {
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.quiz_viewpager);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + i);
    }

    private void setAnime(View view) {
        //正解アニメーション
        maruAnime = new AlphaAnimation(0, 1);
        maruAnime.setDuration(1000);
        maruAnime.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            //表示され終わったとき
            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewPager(1);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        //不正解アニメーション
        batuAnime = new AlphaAnimation(0, 1);
        batuAnime.setDuration(1000);
        batuAnime.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            //表示され終わったとき
            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewPager(1);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    //選択肢をクリックされたときの動作
    View.OnClickListener choiceButtonClickListener = new View.OnClickListener() {
        // 選択肢がクリックされた時の処理
        @Override
        public void onClick(View v) {
            //ボタンを押せなくする。
            choice1.setEnabled(false);
            choice2.setEnabled(false);
            choice3.setEnabled(false);

            // 押されたボタンのテキストと正解を比較
            if (quizDetail.isAnswer(String.valueOf(((Button) v).getText()))) {
                // 正解の処理
                QuizActivity.ans++;
                maru_img.setVisibility(View.VISIBLE);
                maru_img.startAnimation(maruAnime);
            } else {
                // 不正解の処理
                batu_img.setVisibility(View.VISIBLE);
                batu_img.startAnimation(batuAnime);
            }
        }
    };
}
