package com.sunbeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MoviesDAO implements AutoCloseable{
	
	private Connection con;
	private PreparedStatement displayAll;	
	
	@Override
	public void close() throws Exception {
		try {
			if (displayAll!=null) {
				displayAll.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public MoviesDAO() throws Exception {
		con = DbUtil.getConnection();
		displayAll= con.prepareStatement("SELECT * from movies");
	}
	
	public ArrayList<Movie> displayAll() throws SQLException {
		ResultSet rs = displayAll.executeQuery();
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			java.sql.Date sqlDate = rs.getDate("released");
			java.util.Date released = new java.util.Date(sqlDate.getTime());
			
			Movie m = new Movie(id, title, released);
			movieList.add(m);
		}
		return movieList;
	};
}
