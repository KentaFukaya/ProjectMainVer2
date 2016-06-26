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
class ZukanSQLiteOpenHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "Zukan.db";
    static final String TABLE_NAME = "zukanDetail";
    static final int DB_VERSION = 2gi;

    static final String CREATE_TABLE = "create table " + TABLE_NAME + "( " +
            "id integer primary key , " +
            "ImageUrl integer not null, " +
            "FishName text not null, " +
            "Abstract text not null, " +
            "Contents0 text not null,"   +
            "Contents1 text not null,"   +
            "Contents2 text not null);";

    static final String DROP_TABLE = "drop table " + TABLE_NAME + ";";

    public ZukanSQLiteOpenHelper(Context c) {
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