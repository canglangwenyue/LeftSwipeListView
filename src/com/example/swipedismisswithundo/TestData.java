package com.example.swipedismisswithundo;

public class TestData {

	private String title;
	private String time;
	private int id;

	public TestData() {
		this.title = null;
		this.time = null;
	}

	public TestData(String title, String time) {
		this.title = title;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
