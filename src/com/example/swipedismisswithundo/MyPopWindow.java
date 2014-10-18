package com.example.swipedismisswithundo;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class MyPopWindow extends PopupWindow {

	private View mMenuView; // PopupWindow上面装载的View

	public MyPopWindow(final Activity context) {
		super(context);

		/* 将xml布局初始化为View,并初始化上面的控件 */
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.mypopwindow, null);
		// 设置SignPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SignPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SignPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SignPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SignPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimBottom);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x80000000);
		// 设置SignPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// 防止虚拟软键盘被弹出菜单遮住
		this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

	}

}
