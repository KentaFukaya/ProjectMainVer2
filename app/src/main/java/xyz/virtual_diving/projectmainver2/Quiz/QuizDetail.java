package xyz.virtual_diving.projectmainver2.Quiz;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by b1014100 on 2016/06/13.
 */
public class QuizDetail implements Serializable {
    private String content;
    private String choices[];
    private String answer;
    private int ImageUrl;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
        answer = choices[0];
    }

    public int getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(int imageUrl) {
        ImageUrl = imageUrl;
    }

    public boolean isAnswer(String choice){
        return this.answer.equals(choice);
    }

    //選択肢をシャッフル
    public void shuffleChoices(){
        // 配列からListへ変換します。
        List<String> list= Arrays.asList(getChoices());
        // リストの並びをシャッフルします。
        Collections.shuffle(list);
        // listから配列へ戻します。
        String[] choices2 = list.toArray(new String[list.size()]);
        //シャッフルしたものをセットする
        this.choices = choices2;
    }
}
