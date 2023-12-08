package com.sunbeam;

import java.util.Scanner;

public class ReviewS {

	public static Review write(Scanner sc) {
		System.out.print("Enter Movie ID: ");
		int movieId = sc.nextInt();
		System.out.print("Enter Review: ");
		sc.nextLine();
		String review = sc.nextLine();
		System.out.print("Enter Rating: ");
		int rating = sc.nextInt();
		
		Review r = new Review(movieId, review, rating);
		return r;
	}
	
	public static Review editReview(Scanner sc) {
		System.out.print("Enter Movie ID: ");
		int movieId = sc.nextInt();
		System.out.print("Enter Review: ");
		sc.nextLine();
		String review = sc.nextLine();
		System.out.print("Enter Rating: ");
		int rating = sc.nextInt();
		Review r = new Review(movieId, review, rating);
		return r;
	}
}
