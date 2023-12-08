package com.sunbeam;

import java.util.Date;
import java.util.Objects;

public class Review {
	int id;
	int movieId;
	String ReviewText;
	int rating;
	int userId;
	java.util.Date modified;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getReviewText() {
		return ReviewText;
	}
	public void setReviewText(String ReviewText) {
		this.ReviewText = ReviewText;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public java.util.Date getModified() {
		return modified;
	}
	public void setModified(java.util.Date modified) {
		this.modified = modified;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, modified, movieId, rating, ReviewText, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "\u001B[33mID:" + id + "  | Movie ID:" + movieId + ", Review:" + ReviewText + ", Rating:" + rating
				+ ", User ID:" + userId + ", Last Modified:" + modified + "\u001B[0m";
	}
	public Review(int id, int movieId, String ReviewText, int rating, int userId, Date modified) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.ReviewText = ReviewText; 
		if (rating>=0 && rating<=10) {
			this.rating = rating;			
		}
		else {
			this.rating = 5;
		}
		this.userId = userId;
		this.modified = modified;
	}
	
	public Review(int movieId, String reviewText, int rating) {
		super();
		this.movieId = movieId;
		ReviewText = reviewText;
		this.rating = rating;
	}
	public Review(int movieId, String reviewText, int rating, int id) {
		super();
		this.movieId = movieId;
		ReviewText = reviewText;
		this.rating = rating;
		this.id = id;
	}
	
	
	
	
}
