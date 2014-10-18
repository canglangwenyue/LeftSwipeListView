package com.example.swipedismisswithundo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestDataHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "todolistManager";

	// tasks table name
	private static final String TABLE_TASKS = "todolist";

	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_TASKNAME = "title";
	private static final String KEY_STATUS = "time";

	public TestDataHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_TASKNAME + " TEXT, " + KEY_STATUS + " INTEGER)";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
		// Create tables again
		onCreate(db);

	}

	public void save(TestData testdata) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TASKNAME, testdata.getTitle());
		values.put(KEY_STATUS, testdata.getTime());
		db.insert(TABLE_TASKS, null, values);
		db.close();
	}

	public List<TestData> getAllTasks() {
		List<TestData> taskList = new ArrayList<TestData>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				TestData task = new TestData();
				task.setId(cursor.getInt(0));
				task.setTitle(cursor.getString(1));
				task.setTime(cursor.getString(2));
				// Adding contact to list
				taskList.add(task);
			} while (cursor.moveToNext());
		}
		return taskList;
	}

	// public void updateTask(Task task) {
	// // updating row
	// SQLiteDatabase db = this.getWritableDatabase();
	// ContentValues values = new ContentValues();
	// values.put(KEY_TASKNAME, task.getTaskName());
	// values.put(KEY_STATUS, task.getStatus());
	// db.update(TABLE_TASKS, values, KEY_ID + " = ?",
	// new String[] { String.valueOf(task.getId()) });
	// db.close();
	// }
	//
	public void deleteTask(String taskname) {
		SQLiteDatabase db = this.getWritableDatabase();
		String[] args = { String.valueOf(taskname) };
		db.delete(TABLE_TASKS, KEY_TASKNAME + "=?", args);
		db.close();
	}

}
