package com.sunbeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class UserDAO implements AutoCloseable{
	
	private Connection con;
	private PreparedStatement signUp;
	private PreparedStatement signIn;
	private PreparedStatement signedInUserName;
	private PreparedStatement editProfile;
	private PreparedStatement updatePassword;
	private PreparedStatement displayUsers;
	
	@Override
	public void close() throws Exception {
		try {
			if (displayUsers!=null) {
				displayUsers.close();
			}
			if (updatePassword != null) {
				updatePassword.close();
			}
			if (editProfile != null) {
				editProfile.close();
			}
			if (signedInUserName!=null) {
				signedInUserName.close();
			}
			if (signIn!=null) {
				signIn.close();
			}
			if (signUp!=null) {
				signUp.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e	.printStackTrace();
		}
		
	}

	public UserDAO() throws Exception {
		con = DbUtil.getConnection();
		signUp = con.prepareStatement("INSERT INTO users (first_name,last_name,email,password,mobile,birth) values (?,?,?,?,?,?)");
		signIn = con.prepareStatement("SELECT password FROM users where email = ?");
		signedInUserName = con.prepareStatement("SELECT id,first_name, last_name FROM users where email = ?");
		editProfile = con.prepareStatement("UPDATE users SET first_name =?,last_name=?,email=?,mobile=?,birth=? WHERE id =?");
		updatePassword = con.prepareStatement("UPDATE users SET password=? WHERE id=?");
		displayUsers = con.prepareStatement("select * from users");
	}
	
	public ArrayList<User> displayAllUsers() throws SQLException {
		ResultSet rs = displayUsers.executeQuery();
		ArrayList<User> userList = new ArrayList<User>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String firstName =rs.getString("first_name");
			String lastName =rs.getString("last_name");
			String email = rs.getString("email");
			User u= new User(id, firstName, lastName, email);
			userList.add(u);
		}
		return userList;
	}
	
	public int addNewUser(User u) throws Exception {
			signUp.setString(1, u.getFirstName());
			signUp.setString(2, u.getLastName());
			signUp.setString(3, u.getEmail());
			signUp.setString(4, u.getPassword());
			signUp.setString(5, u.getMobile());
			java.util.Date utilDate =u.getBirth();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			signUp.setDate(6, sqlDate);
			
			int count = signUp.executeUpdate();
			return count;
	}
	
	public boolean signInCheck(User u) throws Exception{
		signIn.setString(1, u.getEmail());
		String pass="";
		ResultSet rs = signIn.executeQuery();
		if (rs.next()) {
			pass = rs.getString("password");
		}
		if(pass.equals(u.getPassword())) {
			return true;
		}else {
			return false;
		}		
	}
	
	public User signedInUserInfo(User u) throws Exception{
		signedInUserName.setString(1, u.getEmail());
		ResultSet rs = signedInUserName.executeQuery();
		String firstName="";
		String lastName="";
		int id = 0;
		if (rs.next()) {
			id = rs.getInt("id");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
		}
		u.setId(id);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		return u;
	}
	
	public int editUserInfo(User u) throws SQLException {
		editProfile.setInt(6, u.getId());
		editProfile.setString(1, u.getFirstName());
		editProfile.setString(2, u.getLastName());
		editProfile.setString(3, u.getEmail());
		editProfile.setString(4, u.getMobile());
		java.util.Date utilDate =u.getBirth();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		editProfile.setDate(5, sqlDate);
		
		int count = editProfile.executeUpdate();
		return count;
	}
	
	public int updatePass(User u) throws SQLException {
		updatePassword.setInt(2, u.getId());
		updatePassword.setString(1, u.getPassword());
		
		int count = updatePassword.executeUpdate();
		return count;
	}
}
