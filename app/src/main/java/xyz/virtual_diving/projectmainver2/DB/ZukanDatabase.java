package xyz.virtual_diving.projectmainver2.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import xyz.virtual_diving.projectmainver2.ZukanDetail.ZukanDetaiActivity;
import xyz.virtual_diving.projectmainver2.ZukanDetail.ZukanDetail;
import xyz.virtual_diving.projectmainver2.ZukanList.ZukanListActivity;
import xyz.virtual_diving.projectmainver2.ZukanList.ZukanListItem;

/*
 * Created by b1014169 on 2016/06/15.
 */
public class ZukanDatabase {
    private static SQLiteDatabase mDb;
    private static String[] FROM = {"id", "ImageUrl", "FishName", "Abstract", "Contents0", "Contents1", "Contents2"};
    private static String ORDER_BY = "id" + " ASC";//並べる順

    // データベースに登録する。
    public static void setZukanData(int id, int ImageID, String FishName, String Abstract, String[] Contents) {
        ZukanSQLiteOpenHelper helper = new ZukanSQLiteOpenHelper(ZukanListActivity.getContext());
        mDb = helper.getWritableDatabase();
        Cursor c = mDb.query(ZukanSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行
        if(!c.moveToPosition(id)) {

            // ContentValuesにデータを格納
            ContentValues values = new ContentValues();
            // カラム名に値を渡す
            values.put("id", id);
            values.put("ImageUrl", ImageID);
            values.put("FishName", FishName);
            values.put("Abstract", Abstract);
            values.put("Contents0", Contents[0]);
            values.put("Contents1", Contents[1]);
            values.put("Contents2", Contents[2]);
            try {
                // データの挿入
                mDb.insert(ZukanSQLiteOpenHelper.TABLE_NAME, null, values);
            } finally {
                mDb.close();
            }
        }
        mDb.close();
        c.close();
    }

    // データベース上のすべてのImageURLとFishNameをZukanItemsにaddする。
    public static void getAllImageUrlandFishName(ArrayList<ZukanListItem> Zukanitems) {
        ZukanSQLiteOpenHelper helper = new ZukanSQLiteOpenHelper(ZukanListActivity.getContext());
        mDb = helper.getWritableDatabase();
        Cursor c = mDb.query(ZukanSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行

            while (c.moveToNext()) {
                ZukanListItem item = new ZukanListItem();
                item.setId(c.getInt(0));
                item.setIcon(c.getInt(1));
                item.setTitle(c.getString(2));
                Zukanitems.add(item);
            }
            c.close();
            mDb.close();
    }


    // id に対応するすべてのデータを返す
    public static void getAllDatabyId(ZukanDetail item, int id) {
        ZukanSQLiteOpenHelper helper = new ZukanSQLiteOpenHelper(ZukanDetaiActivity.getContext());
        mDb = helper.getWritableDatabase();

        String[] contents = new String[3];
        Cursor c = mDb.query(ZukanSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行
            if (c.moveToPosition(id)) {
                item.setId(c.getInt(0));
                item.setImageUrl(c.getInt(1));
                item.setFishName(c.getString(2));
                item.setAbstract(c.getString(3));
                contents[0] = c.getString(4);
                contents[1] = c.getString(5);
                contents[2] = c.getString(6);
                item.setContents(contents);

            }
            c.close();
            mDb.close();

    }

}
