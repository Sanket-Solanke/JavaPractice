package com.sunbeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAO implements AutoCloseable{
	
	private Connection con;
	private PreparedStatement displayAllReviews;
	private PreparedStatement writeReview;
	private PreparedStatement updateReview;
	private PreparedStatement getReviewId;
	private PreparedStatement displayMyReviews;
	private PreparedStatement deleteReview;
	private PreparedStatement shareReview;
	private PreparedStatement displayReviewsSharedWithMe;
	

	@Override
	public void close() throws Exception {
		try {
			if (displayReviewsSharedWithMe != null) {
				displayReviewsSharedWithMe.close();
			}
			if (shareReview != null) {
				shareReview.close();
			}
			if (deleteReview != null) {
				deleteReview.close();
			}
			if (getReviewId!= null) {
				getReviewId.close();
			}
			if (updateReview != null) {
				updateReview.close();
			}
			if (writeReview != null) {
				writeReview.close();
			}
			if (displayAllReviews!=null) {
				displayAllReviews.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	public ReviewDAO() throws Exception {
		con = DbUtil.getConnection();
		displayAllReviews= con.prepareStatement("SELECT * from reviews");
		writeReview = con.prepareStatement("INSERT INTO reviews (movie_id,review,rating,user_id) values (?,?,?,?)");
		updateReview = con.prepareStatement("UPDATE reviews SET movie_id=?,review=?,rating=? WHERE id =?");
		getReviewId = con.prepareStatement("SELECT user_id from reviews where id=?");
		displayMyReviews= con.prepareStatement("SELECT * from reviews where user_id =?");
		deleteReview = con.prepareStatement("DELETE from reviews WHERE id =?");
		shareReview = con.prepareStatement("INSERT into shares values(?,?)");
		displayReviewsSharedWithMe = con.prepareStatement("SELECT reviews.* from shares,reviews where shares.review_id=reviews.id and shares.user_id=?");
	}
	
	public ArrayList<Review> displayRevsSharedWithMe(int myId) throws SQLException {
		displayReviewsSharedWithMe.setInt(1, myId);	
		ResultSet rs = displayReviewsSharedWithMe.executeQuery();
		ArrayList<Review> reviewList = new ArrayList<Review>();
		while (rs.next()) {
			int id = rs.getInt("reviews.id");
			int movieId = rs.getInt("reviews.movie_id");
			String reviewText = rs.getString("reviews.review");
			int rating = rs.getInt("reviews.rating");
			int userId = rs.getInt("reviews.user_id");
			java.sql.Date sqlDate = rs.getDate("reviews.modified");
			java.util.Date modified= new java.util.Date(sqlDate.getTime());
		
			Review r = new Review(id, movieId, reviewText, rating, userId, modified);
			reviewList.add(r);
		}
		return reviewList;
		}
	
	public void share(int reviewId,int userId) throws SQLException {
		shareReview.setInt(1, reviewId);
		shareReview.setInt(2, userId);
		shareReview.executeUpdate();
	}
	
	public int writeReview(Review r, int uid) throws SQLException {
		writeReview.setInt(1, r.getMovieId());
		writeReview.setString(2, r.getReviewText());
		writeReview.setInt(3, r.getRating());
		writeReview.setInt(4, uid);
		
		int count = writeReview.executeUpdate();
		return count;
	}
	
	public ArrayList<Review> displayAllRev() throws SQLException {
		ResultSet rs = displayAllReviews.executeQuery();
		ArrayList<Review> reviewList = new ArrayList<Review>();
		while (rs.next()) {
			int id = rs.getInt("id");
			int movieId = rs.getInt("movie_id");
			String reviewText = rs.getString("review");
			int rating = rs.getInt("rating");
			int userId = rs.getInt("user_id");
			java.sql.Date sqlDate = rs.getDate("modified");
			java.util.Date modified= new java.util.Date(sqlDate.getTime());
		
			Review r = new Review(id, movieId, reviewText, rating, userId, modified);
			reviewList.add(r);
		}
		return reviewList;
	}
	
	public int getReviewUserId(int id) throws SQLException {
		getReviewId.setInt(1, id);
		ResultSet rs = getReviewId.executeQuery();
		if (rs.next()) {
			int userId = rs.getInt("user_id");
			return userId;
		}
		return 0;
	}
	
	public void editReview(Review r, int id ) throws SQLException {
		updateReview.setInt(1, r.getMovieId());
		updateReview.setString(2, r.getReviewText());
		updateReview.setInt(3, r.getRating());
		updateReview.setInt(4, id);
		updateReview.executeUpdate();
	}
	
	public ArrayList<Review> myReviews(int uId) throws SQLException {
		displayMyReviews.setInt(1, uId);
		ResultSet rs = displayMyReviews.executeQuery();
		ArrayList<Review> reviewList = new ArrayList<Review>();
		while (rs.next()) {
			int id = rs.getInt("id");
			int movieId = rs.getInt("movie_id");
			String reviewText = rs.getString("review");
			int rating = rs.getInt("rating");
			int userId = rs.getInt("user_id");
			java.sql.Date sqlDate = rs.getDate("modified");
			java.util.Date modified= new java.util.Date(sqlDate.getTime());
		
			Review r = new Review(id, movieId, reviewText, rating, userId, modified);
			reviewList.add(r);
		}
		return reviewList;
	}
	
	public void delReview(int delId, int id ) throws SQLException {
		deleteReview.setInt(1, delId);
		deleteReview.executeUpdate();
	}
	
}
