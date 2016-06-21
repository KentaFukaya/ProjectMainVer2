package xyz.virtual_diving.projectmainver2.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import xyz.virtual_diving.projectmainver2.MovieList.ListItem;
import xyz.virtual_diving.projectmainver2.MovieList.MovieListActivity;

/**
 * Created by b1014169 on 2016/06/15.
 */
public class VideoListDatabase {
    private static SQLiteDatabase mDb;
    private static String[] FROM = {"id", "TitleText", "ContentsText", "ImageID", "ViewCount", "MovieURL"};
    private static String ORDER_BY = "id" + " ASC";//並べる順

    // データの追加
    public static void setData(int id, String Title, String Contents, int ImageID, int ViewCount, String url) {
        VideoListSQLiteOpenHelper helper = new VideoListSQLiteOpenHelper(MovieListActivity.getContext());
        mDb = helper.getWritableDatabase();

        // ContentValuesにデータを格納
        ContentValues values = new ContentValues();
        // カラム名に値を渡す
        values.put("id", id);
        values.put("TitleText", Title);
        values.put("ContentsText", Contents);
        values.put("ImageID", ImageID);
        values.put("ViewCount", ViewCount);
        values.put("MovieURL", url);
        try {
            // データの挿入
            mDb.insert(VideoListSQLiteOpenHelper.TABLE_NAME, null, values);
        } finally {
            mDb.close();
        }
    }

    // id に対応するデータを返す
    public static void getAllData(List<ListItem> list) {
        VideoListSQLiteOpenHelper helper = new VideoListSQLiteOpenHelper(MovieListActivity.getContext());
        mDb = helper.getWritableDatabase();
        Cursor c = mDb.query(VideoListSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行
        try {
            while (c.moveToNext()) {
                ListItem item = new ListItem();
                item.setListId(c.getInt(0));
                item.setTitleText(c.getString(1));
                item.setContentsText(c.getString(2));
                item.setImageId(c.getInt(3));
                item.setViewCount(c.getInt(4));
                item.setMovieURL(c.getString(5));
                list.add(item);
            }
        } finally {
            c.close();
            mDb.close();
        }

    }

    // id に対応するデータを返す
    public static ListItem getPositionData(int id) {
        VideoListSQLiteOpenHelper helper = new VideoListSQLiteOpenHelper(MovieListActivity.getContext());
        mDb = helper.getWritableDatabase();

        ListItem item = new ListItem();
        Cursor c = mDb.query(VideoListSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行
        try {
            if (c.moveToPosition(id)) {
                item.setListId(c.getInt(0));
                item.setTitleText(c.getString(1));
                item.setContentsText(c.getString(2));
                item.setImageId(c.getInt(3));
                item.setViewCount(c.getInt(4));
                item.setMovieURL(c.getString(5));
            }
        } finally {
            c.close();
            mDb.close();
        }
        return item;
    }

    //データベースの内容の変更
    //http://www.javadrive.jp/android/sqlite_data/index7.html
    public static void updateRow(int id, String Title, String Contents, int ImageID, int ViewCount, String url) {
        VideoListSQLiteOpenHelper helper = new VideoListSQLiteOpenHelper(MovieListActivity.getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        // ContentValuesにデータを格納
        ContentValues values = new ContentValues();
        // カラム名に値を渡す
        values.put("id", id);
        values.put("TitleText", Title);
        values.put("ContentsText", Contents);
        values.put("ImageID", ImageID);
        values.put("ViewCount", ViewCount);
        values.put("MovieURL", url);
        db.update(VideoListSQLiteOpenHelper.TABLE_NAME, values, "id =" + id, null);
    }
}
