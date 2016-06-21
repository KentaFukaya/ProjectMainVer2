package xyz.virtual_diving.projectmainver2.DB;

/**
 * Created by b1014169 on 2016/06/08.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelperのサブクラスSampleSQLiteOpenHelperの実装
 */
class VideoListSQLiteOpenHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "videolist.db";
    static final String TABLE_NAME = "videolistevents";
    static final int DB_VERSION = 1;

    static final String CREATE_TABLE = "create table " + TABLE_NAME + "( " +
            "id integer primary key , " +
            "TitleText text not null, " +
            "ContentsText text not null, " +
            "ImageID integer not null, " +
            "ViewCount integer not null, " +
            "MovieURL text not null);";

    static final String DROP_TABLE = "drop table " + TABLE_NAME + ";";

    public VideoListSQLiteOpenHelper(Context c) {
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