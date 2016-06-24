package xyz.virtual_diving.projectmainver2.Quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import xyz.virtual_diving.projectmainver2.R;

public class QuizPreResultFragment extends Fragment {

    int ans;

    public QuizPreResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.quizactivity_fragment_quizresult, container, false);

        ans = QuizActivity.ans;

        //結果発表ボタン以外消す
        view.findViewById(R.id.quiz_resultPoint).setVisibility(View.GONE);
        view.findViewById(R.id.quiz_resultMax).setVisibility(View.GONE);
        view.findViewById(R.id.quiz_quit).setVisibility(View.GONE);

        Button result = (Button) view.findViewById(R.id.result);
        result.setVisibility(View.VISIBLE);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.quiz_viewpager);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        return view;
    }



}
