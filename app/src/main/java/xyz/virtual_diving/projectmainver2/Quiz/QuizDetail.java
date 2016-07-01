package xyz.virtual_diving.projectmainver2.Quiz;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Created by b1014159
 */
public class QuizDetail implements Serializable {

    private  int id;
    private  int fishId;
    private String question;
    private String choices[];
    private String answer;
    private int ImageUrl;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFishId() {
        return fishId;
    }

    public void setFishId(int fishId) {
        this.fishId = fishId;
    }

    //選択肢をシャッフル
    public void shuffleChoices(){
        // 配列からListへ変換します。
        List<String> list= Arrays.asList(getChoices());
        // リストの並びをシャッフルします。
        Collections.shuffle(list);
        //listから配列へ戻し、シャッフルしたものをセットする
        this.choices = list.toArray(new String[list.size()]);
    }
}
