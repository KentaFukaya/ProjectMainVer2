package xyz.virtual_diving.projectmainver2.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import xyz.virtual_diving.projectmainver2.Quiz.QuizActivity;
import xyz.virtual_diving.projectmainver2.Quiz.QuizDetail;

/*
 * Created by b1014159
 */
public class QuizDatabase {
    private static SQLiteDatabase mDb;
    private static String[] FROM = {"id", "ImageUrl", "fishId", "question", "choice1", "choice2", "choice3"};
    private static String ORDER_BY = "id" + " ASC";//並べる順

    // データベースに登録する。
    public static void setQuizData(QuizDetail quizDetail) {
        QuizSQLiteOpenHelper helper = new QuizSQLiteOpenHelper(QuizActivity.getContext());
        mDb = helper.getWritableDatabase();
        Cursor c = mDb.query(QuizSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行
        if(!c.moveToPosition(quizDetail.getId())) {

            // ContentValuesにデータを格納
            ContentValues values = new ContentValues();
            // カラム名に値を渡す
            values.put("id", quizDetail.getId());
            values.put("ImageUrl", quizDetail.getImageUrl());
            values.put("fishId", quizDetail.getFishId());
            values.put("question", quizDetail.getQuestion());
            values.put("choice1", quizDetail.getChoices()[0]);
            values.put("choice2", quizDetail.getChoices()[1]);
            values.put("choice3", quizDetail.getChoices()[2]);
            try {
                // データの挿入
                mDb.insert(QuizSQLiteOpenHelper.TABLE_NAME, null, values);
            } finally {
                mDb.close();
            }
        }
        mDb.close();
        c.close();
    }

    // データベース上のすべてのdataをQuizDetailsで返す。
    public static ArrayList<QuizDetail> getQuizDetailsAll() {
        ArrayList<QuizDetail> quizDetails = new ArrayList<>();

        QuizSQLiteOpenHelper helper = new QuizSQLiteOpenHelper(QuizActivity.getContext());
        mDb = helper.getReadableDatabase();
        Cursor c = mDb.query(QuizSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            QuizDetail quizDetail = new QuizDetail();
            quizDetail.setId(c.getInt(0));
            quizDetail.setImageUrl(c.getInt(1));
            quizDetail.setFishId(c.getInt(2));
            quizDetail.setQuestion(c.getString(3));
            quizDetail.setChoices(new String[]{c.getString(4), c.getString(5), c.getString(6)});
            //選択肢をシャッフルする
            quizDetail.shuffleChoices();
            quizDetails.add(quizDetail);
        }
        c.close();
        mDb.close();

        return quizDetails;
    }

}
