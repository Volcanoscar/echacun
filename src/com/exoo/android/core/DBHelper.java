package com.exoo.android.core;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.exoo.android.core.express.ExpressCom;
import com.exoo.android.core.express.ExpressService;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "list.db";
	private static final String TBL_NAME = "express_com";
	private static final String CREATE_TBL = " create table  express_com(_key text,_value text) ";

	private SQLiteDatabase db;

	public DBHelper(Context c) {
		super(c, DB_NAME, null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQLiteDatabase.openOrCreateDatabase("/data/data/com.database/databases/temp2.db",
		// null);
		this.db = db;
		db.execSQL(CREATE_TBL);

		List<ExpressCom> lst = new ExpressService().GetExpressCom();
		if (lst != null) {
			for (int i = 0; i < lst.size(); i++) {
				// insert into config(_key,_value)
				// values('NeedSleepTime','180');
				// String sql =
				// "INSERT INTO "+TBL_NAME+" (_key,_value) VALUES ('"+lst.get(i).GetID()+"','"+lst.get(i).GetValue()+"')";
				ContentValues values = new ContentValues();
				
				values.put("_key", lst.get(i).GetID());
				values.put("_value", lst.get(i).GetValue()); // 分钟为单位
				db.insert(TBL_NAME, "null", values);

			}
		}
	}

	public void insert(ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TBL_NAME, null, values);
		db.close();
	}

	/**
	 * 查存
	 * 
	 * @param table
	 *            表名
	 * @param columns
	 *            列
	 * @param selection
	 *            需要查询的列
	 * @param selectionArgs
	 *            需要查询的列的参数
	 * @param groupBy
	 *            分组
	 * @param having
	 *            where
	 * @param orderBy
	 *            排序
	 * @return
	 */
	public Cursor query(String table, String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(table, columns, selection, selectionArgs, groupBy,
				having, orderBy);
		return c;
	}

	// public void del(int id) {
	// if (db == null)
	// db = getWritableDatabase();
	// db.delete(TBL_NAME, "_id=?", new String[] { String.valueOf(id) });
	// }
	public void close() {
		if (db != null)
			db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void ExecSQL(String sql) {
		try {
			SQLiteDatabase db = getWritableDatabase();
			ExecSQL(sql, db);
		} catch (Exception e) {
			Log.e("ExecSQL Exception", e.getMessage());
			e.printStackTrace();
		}
	}

	public void ExecSQL(String sql, SQLiteDatabase db) {
		try {
			db.execSQL(sql);
			Log.i("ExecSQL", sql);
		} catch (Exception e) {
			Log.e("ExecSQL Exception", e.getMessage());
			e.printStackTrace();
		}
	}

	public List<ExpressCom> getExpressCom() {
		String sql = "select * from express_com";
		SQLiteDatabase db = getWritableDatabase();
		List<ExpressCom> expresscoms = new ArrayList<ExpressCom>();
		ExpressCom expresscom = null;
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			 
			expresscom = new ExpressCom();
			expresscom.setID(cursor.getString(cursor.getColumnIndex("_key")));
			expresscom.setValue(cursor.getString(cursor.getColumnIndex("_value")));
			expresscoms.add(expresscom);
		}
		return expresscoms;
	}

}

/**
 * 
 ContentValues values = new ContentValues(); values.put("name", "1小时");
 * DBHelper helper = new DBHelper(getApplicationContext());
 * helper.insert(values);
 * 
 * Cursor c = helper.query(); List<String> list = new ArrayList<String>(); while
 * (c.moveToNext()) { list.add(c.getString(c.getColumnIndex("name")) + "-" +
 * c.getString(c.getColumnIndex("_id"))); }
 */
