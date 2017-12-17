package com.example.taehyung.ddac.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.taehyung.ddac.Item.BoughtProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TaeHyungKim on 2017-12-17.
 */

public class DbOpenHelper {



    private static final String DATABASE_NAME = "my_products.db";

    private static final int DATABASE_VERSION = 1;

    public static SQLiteDatabase mDB;

    private DatabaseHelper mDBHelper;

    private Context mCtx;



    private class DatabaseHelper extends SQLiteOpenHelper {
        // 생성자
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DataBases.CreateDB._CREATE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }
    public List<BoughtProduct> getBoughtProductList() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, NAME, AGE, PHONE FROM TEST_TABLE ");
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sb.toString(), null);
        List<BoughtProduct> products = new ArrayList();
        while( cursor.moveToNext() )
            products.add(new BoughtProduct(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3)));
        return products;
    }
    public void close(){
        mDB.close();
    }
}

