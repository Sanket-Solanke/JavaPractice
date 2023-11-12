package com.sunbeam;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int choice =0;
    	
    	do {
             System.out.println("+----------------------+");
             System.out.println("| \u001B[33m0. Exit\u001B[0m              |");
             System.out.println("| 2. Option 2          |");
             System.out.println("| 3. Option 3          |");
             System.out.println("| 4. Option 4          |");
             System.out.println("+----------------------+");
             System.out.print("Enter your choice: ");
             choice = sc.nextInt();
		} while (choice != 0);
    	sc.close();
    }

}
