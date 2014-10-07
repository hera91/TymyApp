package cz.tymy.api.tymyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class for Database handling
 */
public class Sites {

	protected static final String DATABASE_NAME = "sites_db";
	protected static final int DATABASE_VERSION = 2;

	protected static final String TB_NAME = "sites";

	// Special value "_id" useful for using SimpleAdapter
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name"; // Human friendly name (like 'pd'..)
    public static final String COLUMN_URL = "url";  // exact URL with 'http://'
	public static final String COLUMN_USER = "user"; // user used in GET request
    public static final String COLUMN_PASS = "pass";  // password used in GET request
    public static final String COLUMN_ORDER = "show_order";  // order for display, not used yet

	public static final String[] columns = { COLUMN_ID, COLUMN_NAME, COLUMN_URL,
            COLUMN_USER, COLUMN_PASS, COLUMN_ORDER};

	protected static final String ORDER_BY = COLUMN_ID + " DESC";

	private SQLiteOpenHelper openHelper;

	static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
            final String sql = "CREATE TABLE " + TB_NAME + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + COLUMN_NAME + " TEXT NOT NULL, "
                    + COLUMN_URL + " TEXT NOT NULL, "
                    + COLUMN_USER + " TEXT NOT NULL, "
                    + COLUMN_PASS + " TEXT NOT NULL, "
                    + COLUMN_ORDER + " INTEGER"
                    + ");";
            db.execSQL(sql);
        }

		/*
		 * SQL executed after upgrade application
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
			onCreate(db);
		}
	}

	public Sites(Context ctx) {
		openHelper = new DatabaseHelper(ctx);
	}

	public Cursor getSites() {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		return db.query(TB_NAME, columns, null, null, null, null, ORDER_BY);
	}

	public Cursor getSite(long id) {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		String[] selectionArgs = { String.valueOf(id) };
		return db.query(TB_NAME, columns, COLUMN_ID + "= ?", selectionArgs,
				null, null, ORDER_BY);
	}

	public boolean deleteSite(long id) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		String[] selectionArgs = { String.valueOf(id) };

		int deletedCount = db.delete(TB_NAME, COLUMN_ID + "= ?", selectionArgs);
		db.close();
		return deletedCount > 0;
	}

	public long insertSite(String name, String url, String user, String pass) {
		SQLiteDatabase db = openHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, name);
        values.put(COLUMN_URL, url);
		values.put(COLUMN_USER, user);
        values.put(COLUMN_PASS, pass);
        values.put(COLUMN_ORDER, 0); // TODO Order not used yet

		long id = db.insert(TB_NAME, null, values);
		db.close();
		return id;
	}

	public void close() {
		openHelper.close();
	}
}
