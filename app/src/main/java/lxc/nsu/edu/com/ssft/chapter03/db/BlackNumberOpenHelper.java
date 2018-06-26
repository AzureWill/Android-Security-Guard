package lxc.nsu.edu.com.ssft.chapter03.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 123 on 2017/7/11.
 */

public class BlackNumberOpenHelper extends SQLiteOpenHelper{
    public BlackNumberOpenHelper(Context context) {
        super(context, "blackNumber.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table blacknumber (_id integer primary key autoincrement,number varchar(24),name varchar(255),mode integer)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
