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
    public static void setQuizData(int id, int ImageUrl, String fishId, String question, String[] choices) {
        QuizSQLiteOpenHelper helper = new QuizSQLiteOpenHelper(QuizActivity.getContext());
        mDb = helper.getWritableDatabase();
        Cursor c = mDb.query(QuizSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行
        if(!c.moveToPosition(id)) {

            // ContentValuesにデータを格納
            ContentValues values = new ContentValues();
            // カラム名に値を渡す
            values.put("id", id);
            values.put("ImageUrl", ImageUrl);
            values.put("fishId", fishId);
            values.put("question", question);
            values.put("choice1", choices[0]);
            values.put("choice2", choices[1]);
            values.put("choice3", choices[2]);
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
        ArrayList<QuizDetail> quizDetails = new ArrayList<QuizDetail>();

        QuizSQLiteOpenHelper helper = new QuizSQLiteOpenHelper(QuizActivity.getContext());
        mDb = helper.getWritableDatabase();
        Cursor c = mDb.query(QuizSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            QuizDetail quizDetail = new QuizDetail();
            quizDetail.setId(c.getInt(0));
            quizDetail.setImageUrl(c.getInt(1));
            quizDetail.setFishId(c.getInt(2));
            quizDetail.setQuestion(c.getString(3));
            quizDetail.setChoices(new String[]{c.getString(4), c.getString(5), c.getString(6)});
            quizDetails.add(quizDetail);
        }
        c.close();
        mDb.close();

        return quizDetails;
    }


    // id に対応するすべてのデータを返す
//    public static void getAllDatabyId(ZukanDetail item, int id) {
//        ZukanSQLiteOpenHelper helper = new ZukanSQLiteOpenHelper(ZukanDetaiActivity.getContext());
//        mDb = helper.getWritableDatabase();
//
//        String[] contents = new String[3];
//        Cursor c = mDb.query(ZukanSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行
//            if (c.moveToPosition(id)) {
//                item.setId(c.getInt(0));
//                item.setImageUrl(c.getInt(1));
//                item.setFishName(c.getString(2));
//                item.setAbstract(c.getString(3));
//                contents[0] = c.getString(4);
//                contents[1] = c.getString(5);
//                contents[2] = c.getString(6);
//                item.setContents(contents);
//
//            }
//            c.close();
//            mDb.close();
//
//    }

}
