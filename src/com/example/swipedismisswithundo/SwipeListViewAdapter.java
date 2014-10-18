package com.example.swipedismisswithundo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fortysevendeg.swipelistview.SwipeListView;

public class SwipeListViewAdapter extends BaseAdapter {

	private final List<TestData> datas;
	private final Context context;
	private final SwipeListView mSwipeListView;
	private final LayoutInflater inflater;

	public SwipeListViewAdapter(Context context, SwipeListView mSwipeListView,
			List<TestData> datas) {
		this.context = context;
		this.mSwipeListView = mSwipeListView;
		this.datas = datas;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public TestData getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		TestData td = getItem(position);
		ViewHolder vh;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = inflater.inflate(R.layout.package_row, null);
			vh.title = (TextView) convertView.findViewById(R.id.nickname);
			vh.time = (TextView) convertView.findViewById(R.id.time);
			vh.delete = (Button) convertView.findViewById(R.id.delete);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		vh.title.setText(td.getTitle());
		vh.time.setText(td.getTime());
		vh.delete.setLayoutParams(new RelativeLayout.LayoutParams(context
				.getResources().getDisplayMetrics().widthPixels / 3,
				RelativeLayout.LayoutParams.MATCH_PARENT));
		vh.delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mSwipeListView.closeAnimate(position);
				mSwipeListView.dismiss(position);
				TestDataHelper db = new TestDataHelper(context);
				Toast.makeText(context, "bfsjk", Toast.LENGTH_LONG).show();
				db.deleteTask(getItem(position).getTitle());
			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView title;
		TextView time;
		Button delete;
	}
}
