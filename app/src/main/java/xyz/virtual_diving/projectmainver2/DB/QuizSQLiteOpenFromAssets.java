package xyz.virtual_diving.projectmainver2.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * Created by 1014159 on 2016/07/01.
 * http://araramistudio.jimdo.com/%E6%8A%80%E8%A1%93%E6%83%85%E5%A0%B1/%E4%BA%88%E3%82%81%E4%BD%9C%E3%81%A3%E3%81%A6%E3%81%8A%E3%81%84%E3%81%9Fsqlite%E3%82%92%E4%BD%BF%E3%81%86/
 */

public class QuizSQLiteOpenFromAssets extends SQLiteOpenHelper {
    private Context mContext;
    private String  mDBName;
    private boolean mUpgrade;

    public QuizSQLiteOpenFromAssets(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        mDBName  = name;
        mUpgrade = false;
    }

    protected void resourceCopyFromAsset() throws IOException {
        Log.d("", "resourceCopyFromAsset: ");
        File of;
        File         od;
        InputStream is;
        OutputStream os;
        byte[]       buf;
        int          size;

        of  = mContext.getDatabasePath(mDBName);
        od  = new File(of.getParent());
        od.mkdirs();

        is  = mContext.getAssets().open(mDBName);
        os  = new FileOutputStream(of);
        buf = new byte[1024];
        for ( ; ; ) {
            size = is.read(buf);
            if (0 > size) break;
            os.write(buf, 0, size);
        }
        os.flush();
        os.close();
        is.close();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        try {
            SQLiteDatabase db;

            if (! mContext.getDatabasePath(mDBName).exists()) {
                resourceCopyFromAsset();
            }
            db = super.getReadableDatabase();
            if (mUpgrade) {
                close();
                resourceCopyFromAsset();
                db = super.getReadableDatabase();
            }
            return db;
        }
        catch (IOException e) {
            return null;
        }
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        try {
            SQLiteDatabase db;

            if (! mContext.getDatabasePath(mDBName).exists()) {
                resourceCopyFromAsset();
            }
            db = super.getWritableDatabase();
            if (mUpgrade) {
                close();
                resourceCopyFromAsset();
                db = super.getWritableDatabase();
            }
            return db;
        }
        catch (IOException e) {
            return null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        mUpgrade = false;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mUpgrade = true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mUpgrade = true;
    }
}
