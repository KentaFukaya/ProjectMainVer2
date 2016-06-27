package xyz.virtual_diving.projectmainver2.DB;

/**
 * Created by b1014159
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelperのサブクラスSampleSQLiteOpenHelperの実装
 */
class QuizSQLiteOpenHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "Quiz.db";
    static final String TABLE_NAME = "quizDetail";
    static final int DB_VERSION = 1;

    static final String CREATE_TABLE = "create table " + TABLE_NAME + "( " +
            "id integer primary key , " +
            "ImageUrl integer not null, " +
            "fishId integer not null, " +
            "question text not null, " +
            "choice1 text not null,"   +
            "choice2 text not null,"   +
            "choice3 text not null);";

    static final String DROP_TABLE = "drop table " + TABLE_NAME + ";";

    public QuizSQLiteOpenHelper(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }

    /**
     * データベースファイル初回使用時に実行される処理
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成のクエリを発行
        db.execSQL(CREATE_TABLE);
    }

    /**
     * データベースのバージョンアップ時に実行される処理
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // テーブルの破棄と再作成
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}