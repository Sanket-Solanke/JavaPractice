package com.sunbeam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Movie {
	int id;
	String title;
	java.util.Date released;
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
	public java.util.Date getReleased() {
		return released;
	}
	public void setReleased(java.util.Date released) {
		this.released = released;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, released, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		return "\u001B[33m| ID=" + id + "  | Title=" + title + "  Release Date=" + sdf.format(released)+ " \u001B[0m";
	}
	
	public Movie(int id, String title, Date released) {
		super();
		this.id = id;
		this.title = title;
		this.released = released;
	}
	
	
	
	
}
