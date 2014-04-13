package com.ieee.imdbupdates;

public class Todo {

	private int id;
	
	private String series;
	private String epi_name;
	private String date;
	private String epi_info;
	public String getTime() {
		return date;
	}

	public void setTime(String time) {
		this.date = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getepi_info() {
		return epi_info;
	}

	public void setepi_info(String text) {
		this.epi_info = text;
	}

	public String getText2() {
		return epi_name;
	}

	public void setText(String title,String text) {
		this.series = text;
				this.epi_name=title;
	}
	public String getText1() {
		return series;
	}
}
