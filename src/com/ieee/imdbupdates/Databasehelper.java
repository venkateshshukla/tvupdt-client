package com.ieee.imdbupdates;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Databasehelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "sample";

	// Contacts table name
	static final String TABLE_TV = "TV";

	// Contacts Table Columns names
	static final String KEY_ID = "id";
	static final String KEY_SERIES = "series";
	static final String KEY_EPI_NAME = "epi_name";
	static final String KEY_EPI_INFO = "epi_info";
	static final String KEY_SELECT = "selected";
	static final String KEY_UIDS = "uids";
	static final String KEY_ONGOING = "ongoing";
	static final String KEY_DEFINITE = "definite";
	private static final String KEY_DATE = "date";
	private SQLiteDatabase myDataBase;
	private static String DB_NAME = "sample";
	private static String DB_PATH = "";

	private final Context myContext;
	protected static final String TAG = "Harshit";

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public Databasehelper(Context context) {
		super(context, DB_NAME, null, 1);// 1? its Database Version
		if (android.os.Build.VERSION.SDK_INT >= 4.2) {
			DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
		} else {
			DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
		}
		this.myContext = context;
	}

	public void createDataBase() throws IOException {
		// If database not exists copy it from the assets

		boolean mDataBaseExist = checkDataBase();
		if (!mDataBaseExist) {
			this.getReadableDatabase();
			this.close();
			try {
				// Copy the database from assests
				copyDataBase();
				System.out.print("createDatabase database created");
			} catch (IOException mIOException) {
				throw new Error("ErrorCopyingDataBase");
			}
		}
	}

	// Check that the database exists here: /data/data/your package/databases/Da
	// Name
	private boolean checkDataBase() {
		File dbFile = new File(DB_PATH + DB_NAME);
		// Log.v("dbFile", dbFile + "   "+ dbFile.exists());
		return dbFile.exists();
	}

	// Copy the database from assets
	private void copyDataBase() throws IOException {
		InputStream mInput = myContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream mOutput = new FileOutputStream(outFileName);
		byte[] mBuffer = new byte[1024];
		int mLength;
		while ((mLength = mInput.read(mBuffer)) > 0) {
			mOutput.write(mBuffer, 0, mLength);
		}
		mOutput.flush();
		mOutput.close();
		mInput.close();
	}

	// Open the database, so we can query it
	public boolean openDataBase() throws SQLException {
		String mPath = DB_PATH + DB_NAME;
		// Log.v("mPath", mPath);
		myDataBase = SQLiteDatabase.openDatabase(mPath, null,
				SQLiteDatabase.CREATE_IF_NECESSARY);
		// mDataBase = SQLiteDatabase.openDatabase(mPath, null,
		// SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		return myDataBase != null;
	}

	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	public Cursor getSeries() throws SQLException {
		System.out.println("we are in get series now");
		Cursor mCursor = null;
		System.out.println("yayy ");

		mCursor = myDataBase.query(true, TABLE_TV, new String[] { KEY_ID,
				KEY_SERIES, KEY_DATE }, null, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			System.out.println("freak rocksss!!!");
		}
		return mCursor;
	}

	public void updateData(Integer select, Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_SELECT, select);

		// updating row
		db.update(TABLE_TV, values, KEY_ID + "=" + id, null);
		db.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public Cursor getTopic(String serial) {
		try {
			System.out.println("inside getTopic");
			// Cursor mCur = null;

			SQLiteDatabase mDb = this.getReadableDatabase();
			String sql = "select * from TV where series = '" + serial + "'";
			if (serial.equals("Grey's Anatomy")) {
				sql = "select * from TV where id = 1";

			}
			System.out.println("query is finally passing");

			Cursor mCur = mDb.rawQuery(sql, null);
			System.out.println("query is passed ");
			if (mCur != null) {
				mCur.moveToFirst();
			}
			System.out.println("cursor is jjust about to return");
			return mCur;
		} catch (SQLException mSQLException) {
			Log.e(TAG, "getTestData >>" + mSQLException.toString());
			throw mSQLException;
		}
	}

	public List<String> getAllLabels() {
		// TODO Auto-generated method stub
		List<String> labels = new ArrayList<String>();
		System.out.println("inside getallLabel");

		// Select All Query
		String selectQuery = "SELECT * FROM TV WHERE selected = 1";
		System.out.println("hg genious");
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		System.out.println("hg faad hai");
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				labels.add(cursor.getString(1));
			} while (cursor.moveToNext());
			System.out.println("freaking hero");
		}
		cursor.close();
		db.close();
		return labels;

	}
	public void update(String tvid, String rtitle, String status, String rely,
			String epname, String epinfo, String epdate) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
//cv.put(KEY_SERIES,rtitle);
		cv.put(KEY_EPI_NAME,epname);
		cv.put(KEY_EPI_INFO,epinfo);
		cv.put(KEY_DATE, epdate);
		cv.put(KEY_ONGOING,status);
		cv.put(KEY_DEFINITE,rely);
		
		db.update(TABLE_TV, cv,KEY_UIDS + "=" + tvid,null);
		db.close();
		

}
	public List<Todo> getTodos() {

		List<Todo> todoList = new ArrayList<Todo>();

		System.out.println("gettods");
		SQLiteDatabase db = this.getWritableDatabase();
		System.out.println("db");
		String selectQuery = "SELECT * FROM TV WHERE selected = 1";
		Cursor mCursor = db.rawQuery(selectQuery, null);
//		Cursor mCursor = myDataBase.query(true, TABLE_TV, new String[] { KEY_ID,
//				KEY_SERIES, KEY_DATE,KEY_EPI_NAME, KEY_EPI_INFO }, null, null, null, null, null, null);
		
		System.out.println("cursor");
		mCursor.moveToFirst();
		System.out.println("query pass");
		while (!mCursor.isAfterLast()) {
			Todo todo = new Todo();

			todo.setId(mCursor.getInt(0));
			todo.setText(mCursor.getString(1), mCursor.getString(5));
			todo.setTime(mCursor.getString(4));
			todo.setepi_info(mCursor.getString(6));
			todoList.add(todo);
			mCursor.moveToNext();
		}
		System.out.println("list returned");
		return todoList;
	}

}
