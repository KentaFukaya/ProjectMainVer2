package xyz.virtual_diving.projectmainver2.Quiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import xyz.virtual_diving.projectmainver2.R;

public class QuizResultFragment extends Fragment {

    private QuizActivityFragmentListener listener = null;
    int ans;
    int position = 0;

    public interface QuizActivityFragmentListener {
        void onQuizFragmentEvent1();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 実装されてなかったらException吐かせて実装者に伝える
        if (!(activity instanceof QuizActivityFragmentListener)) {
            throw new UnsupportedOperationException(
                    "Listener is not Implementation.");
        } else {
            // ここでActivityのインスタンスではなくActivityに実装されたイベントリスナを取得
            listener = (QuizActivityFragmentListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.quizactivity_fragment_quizresult, container, false);

        ans = QuizActivity.getAns();
        Bundle args = getArguments();
        position = args.getInt("page");

        //点数表示
        ((TextView) view.findViewById(R.id.quiz_resultPoint)).setText(""+ans);
        ((TextView) view.findViewById(R.id.quiz_resultMax)).setText("/" + (position-1));

        //図鑑に戻るボタン
        Button quit = (Button) view.findViewById(R.id.quiz_quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    // Activityにイベント通知
                    listener.onQuizFragmentEvent1();
                }
            }
        });

        return view;
    }


}
