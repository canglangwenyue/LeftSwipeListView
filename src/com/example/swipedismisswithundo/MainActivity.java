package com.example.swipedismisswithundo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

public class MainActivity extends Activity {

	private Button button;
	protected TestDataHelper db;
	List<TestData> list;
	public SwipeListView mSwipeListView;
	public SwipeListViewAdapter adapter;
	public List<TestData> datas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new TestDataHelper(this);

		mSwipeListView = (SwipeListView) findViewById(R.id.example_lv_list);
		mSwipeListView
				.setOffsetLeft(this.getResources().getDisplayMetrics().widthPixels * 2 / 3);
		mSwipeListView.setSwipeMode(SwipeListView.SWIPE_MODE_RIGHT);
		mSwipeListView.setBackgroundColor(TRIM_MEMORY_MODERATE);
		mSwipeListView.setSwipeActionLeft(SwipeListView.SWIPE_ACTION_REVEAL);
		mSwipeListView.setAnimationTime(3);
		mSwipeListView.setSwipeOpenOnLongPress(false);
		datas = new ArrayList<TestData>();
		inits();
		adapter = new SwipeListViewAdapter(this, mSwipeListView, datas);
		mSwipeListView.setAdapter(adapter);
		mSwipeListView
				.setSwipeListViewListener(new BaseSwipeListViewListener() {

					@Override
					public void onClickFrontView(int position) {
						super.onClickFrontView(position);
						mSwipeListView.closeOpenedItems();
						Toast.makeText(
								MainActivity.this,
								adapter.getItem(position).getTime()
										+ getTitle() + "和你对话", 2400).show();
					}

					@Override
					public void onDismiss(int[] reverseSortedPositions) {
						super.onDismiss(reverseSortedPositions);
						for (int i : reverseSortedPositions) {
							datas.remove(i);
						}
						adapter.notifyDataSetChanged();
					}

				});

	}

	private void inits() {
		// TODO Auto-generated method stub
		List<TestData> datalist = db.getAllTasks();
		for (int i = 0; i < datalist.size(); i++) {
			datas.add(new TestData(datalist.get(i).getTitle(), datalist.get(0)
					.getTime()));
		}

	}

	public void testButton(View view) {
		button = (Button) findViewById(R.id.button1);
		button.getTop();

		final MyPopWindow popupWindow = new MyPopWindow(MainActivity.this);
		popupWindow.showAtLocation(MainActivity.this.findViewById(R.id.main),
				Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		Button btnSure = (Button) popupWindow.getContentView().findViewById(
				R.id.sign_define);
		btnSure.setOnClickListener(new OnClickListener() {

			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText t = (EditText) popupWindow.getContentView()
						.findViewById(R.id.sign_et);
				String s = t.getText().toString();
				Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG);
				if (s.equalsIgnoreCase("")) {
					Toast.makeText(MainActivity.this,
							"enter the task description first!!",
							Toast.LENGTH_LONG);
				} else {
					additems(s);
					Log.d("tasker", "data added");
					t.setText("");
					popupWindow.dismiss();
				}
			}

		});
	}

	/**
	 * add item to listview and databases
	 * 
	 * @param s
	 */
	protected void additems(String s) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日    HH:mm:ss     ");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		TestData datdas = new TestData(s, str);
		datas.add(datdas);
		db.save(datdas);
		mSwipeListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
