package com.sunbeam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class UserS {
	public static User addNewUser(Scanner sc) throws ParseException {
		System.out.print("Enter first name : ");
		String firstname = sc.next();
		System.out.print("Enter last name : ");
		String lastname = sc.next();
		System.out.print("Enter email : ");
		String email = sc.next();
		System.out.print("Enter password : ");
		String password = sc.next();
		System.out.print("Enter mobile : ");
		String mobile = sc.next();
		System.out.print("Enter Birthdate (DD-MM-YYYY): ");
		String dob = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date birth = sdf.parse(dob);
		
		User u = new User(firstname, lastname, email, password, mobile, birth);
		
		return u;
	}
	
	public static User signInStatus(Scanner sc) {
		System.out.print("Enter email : ");
		String email = sc.next();
		System.out.print("Enter password : ");
		String password = sc.next();
		
		User u = new User(email, password);
		
		return u;
	}
	
	public static User editUserInfo (Scanner sc) throws ParseException {
		System.out.print("Enter new first name:");
		String first_name = sc.next();
		System.out.print("Enter new last name:");
		String last_name = sc.next();
		System.out.print("Enter new email:");
		String email = sc.next();
		System.out.print("Enter new mobile:");
		String mobile= sc.next();
		System.out.print("Enter new birth date (DD-MM-YYYY):");
		String dob = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date birth = sdf.parse(dob);
		User u = new User(first_name, last_name, email, dob, mobile, birth);
		
		return u;
	}
	
	public static User updatePassword (Scanner sc) {
		System.out.print("Enter new Password: ");
		String pass1 = sc.next();
		System.out.print("Confirm Password: ");
		String pass2= sc.next();
		if (pass1.compareTo(pass2)!=0) {
			System.out.println("Passwords does not match new password");
			updatePassword(sc);
		}
		else {
			User u = new User(pass1);
			return u;
		}
		return null;
	}
}
