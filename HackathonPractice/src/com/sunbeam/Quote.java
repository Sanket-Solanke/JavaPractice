package com.sunbeam;

import java.util.Objects;

public class Quote {
	int id;
	String text;
	String author;
	int userId;
	
	public Quote(int id, String text, String author, int userId) {
		super();
		this.id = id;
		this.text = text;
		this.author = author;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Quote [id=" + id + ", text=" + text + ", author=" + author + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, text, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quote other = (Quote) obj;
		return Objects.equals(author, other.author) && id == other.id && Objects.equals(text, other.text)
				&& userId == other.userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
