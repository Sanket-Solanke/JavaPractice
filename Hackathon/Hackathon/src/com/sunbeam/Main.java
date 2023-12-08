package com.sunbeam;

import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.protocol.a.NativeConstants.StringSelfDataType;

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	try (ReviewDAO reviewDao = new ReviewDAO();){
			
    		try (MoviesDAO movieDao = new MoviesDAO();){
    			
        		try (UserDAO dao = new UserDAO();){
            		int choice =0;
                	do {
                        System.out.println("\u001B[40m\u001B[92m+----------------------+\u001B[0m");
                        System.out.println("\u001B[40m\u001B[92m| 0. Exit              |\u001B[0m");
                        System.out.println("\u001B[40m\u001B[92m| 1. Sign Up           |\u001B[0m");
                        System.out.println("\u001B[40m\u001B[92m| 2. Sign In           |\u001B[0m");
                        System.out.println("\u001B[40m\u001B[92m+----------------------+\u001B[0m");
                        System.out.print("\n"
                    					+ "\u001B[97mEnter your choice: \u001B[0m");
        	            choice = sc.nextInt();
        	            System.out.println();
        	            switch (choice) {
        	            case 0:
        	            	break;
        	            
        	            case 2:
        	            	User signInUser = UserS.signInStatus(sc);
        	            	User signedInUser = dao.signedInUserInfo(signInUser);
        	            	boolean status =dao.signInCheck(signInUser);
        	            	if (status) {
        						System.out.print("\033[H\033[2J");
        						System.out.flush();
        						Runtime.getRuntime().exec("clear");
        						System.out.println("\n\u001B[32mSuccesfully Signed in as: \u001B[0m" +signedInUser.getFirstName()+" "+signedInUser.getLastName() +"\n");
        					}
        	            	else {
        	            		System.out.println("\n\u001B[91mSign in failed.\u001B[0m \n"
        	            				+ "Enter valid information.\n");
        	            	}
        	            	
        	            	while (status) {
        						System.out.println("\u001B[40m\u001B[92m"
        								+ "+------------------------------------+\n"
        								+ "| 0.  Log Out                        |\n"
        								+ "| 1.  Edit Profile                   |\n"
        								+ "| 2.  Change Password                |\n"
        								+ "| 3.  Write a Review                 |\n"
        								+ "| 4.  Edit Review                    |\n"
        								+ "| 5.  Display all Movies             |\n"
        								+ "| 6.  Display all Reviews            |\n"
        								+ "| 7.  Display my Reviews             |\n"
        								+ "| 8.  Display Reviews Shared with me |\n"
        								+ "| 9.  Share a Review                 |\n"
        								+ "| 10. Delete a Review                |\n"
        								+ "+------------------------------------+\n"
        								+ "\u001B[0m");
        						int key;
        						System.out.print("Enter choice: ");
        						key =sc.nextInt();
        						System.out.println("");
        						switch (key) {
        						case 0:
        							status= false;
        							System.out.print("\033[H\033[2J");
        							System.out.flush();
        							Runtime.getRuntime().exec("clear");
        							System.out.println("\u001B[92mLogged Out Succesfully!\u001B[0m");
        							System.out.println();
        							break;
        						
        						case 1:
        							User u = UserS.editUserInfo(sc);
        							u.setId(signedInUser.getId());
        							dao.editUserInfo(u);
        							System.out.print("\033[H\033[2J");
            						System.out.flush();
            						Runtime.getRuntime().exec("clear");
        							System.out.println("\u001B[92mInformation Updated Succesfully!\u001B[0m\n");
        							break;
        							
        						case 2:
        							User uPass = UserS.updatePassword(sc);
        							uPass.setId(signedInUser.getId());
        							dao.updatePass(uPass);
        							System.out.print("\033[H\033[2J");
            						System.out.flush();
            						Runtime.getRuntime().exec("clear");
        							System.out.println("\u001B[92mPassword Updated Succesfully!\u001B[0m\n");
        							break;
        							
        						case 3:
        							System.out.println("+------------------------------------------------------+");
        							ArrayList<Movie> movieList = movieDao.displayAll();
        							movieList.stream().forEach(e->System.out.println(e.toString()));
        							System.out.println("+------------------------------------------------------+");
        							System.out.println();
        							Review r = ReviewS.write(sc);
        							reviewDao.writeReview(r, signedInUser.getId());
        							System.out.print("\033[H\033[2J");
            						System.out.flush();
            						Runtime.getRuntime().exec("clear");
            						System.out.println("\u001B[92mReview Added Succesfully!\u001B[0m\n");
        							break;
        							
        						case 4:
        							System.out.print("\033[H\033[2J");
            						System.out.flush();
            						Runtime.getRuntime().exec("clear");
        							ArrayList<Review> revList= reviewDao.displayAllRev();
        							revList.stream().forEach(e->System.out.println(e.toString()));
        							System.out.println();
        							System.out.print("Enter ID of review to be edited: ");
        							int id = sc.nextInt();
        							int userId = reviewDao.getReviewUserId(id);
        							if (userId == signedInUser.getId()) {
        								Review re = ReviewS.editReview(sc);
        								reviewDao.editReview(re,id);
        								System.out.print("\033[H\033[2J");
                						System.out.flush();
                						Runtime.getRuntime().exec("clear");
        								System.out.println("\u001B[92mReview Edited Succesfully!\u001B[0m\n");
        								System.out.println();
									}else {
										System.out.print("\033[H\033[2J");
		        						System.out.flush();
		        						Runtime.getRuntime().exec("clear");
        								System.out.println("\u001B[91mYou can only edit your own reviews\u001B[0m");
        								System.out.println();
									}        							
        							break;
        						case 5:
        							System.out.print("\033[H\033[2J");
            						System.out.flush();
            						Runtime.getRuntime().exec("clear");
            						System.out.println("");
        							System.out.println("+------------------------------------------------------+");
        							ArrayList<Movie> movies2List = movieDao.displayAll();
        							movies2List.stream().forEach(e->System.out.println(e.toString()));
        							System.out.println("+------------------------------------------------------+");
        							System.out.println();
        							break;
        						case 6:
        							System.out.print("\033[H\033[2J");
            						System.out.flush();
            						Runtime.getRuntime().exec("clear");
        							ArrayList<Review> rList= reviewDao.displayAllRev();
        							rList.stream().forEach(e->System.out.println(e.toString()));
        							System.out.println();
        							break;
        						case 7:
        							System.out.print("\033[H\033[2J");
            						System.out.flush();
            						Runtime.getRuntime().exec("clear");
        							ArrayList<Review> mRList= reviewDao.myReviews(signedInUser.getId());
        							mRList.stream().forEach(e->System.out.println(e.toString()));
        							System.out.println();
        							break;
        							
        						case 8:
        							System.out.print("\033[H\033[2J");
            						System.out.flush();
            						Runtime.getRuntime().exec("clear");
        							ArrayList<Review> rSWMList= reviewDao.displayRevsSharedWithMe(signedInUser.getId());
        							rSWMList.stream().forEach(e->System.out.println(e.toString()));
        							System.out.println();
        							break;	
        						case 9:
        							System.out.print("\033[H\033[2J");
            						System.out.flush();
            						Runtime.getRuntime().exec("clear");
            						
        							ArrayList<Review> reList= reviewDao.displayAllRev();
        							reList.stream().forEach(e->System.out.println(e.toString()));
        							System.out.println();
        							System.out.print("\nEnter Id of the review you want to share: ");
        							int revId= sc.nextInt();
        							int uId = reviewDao.getReviewUserId(revId);
        							if (uId == signedInUser.getId()) {        								
        								ArrayList<User> uList = dao.displayAllUsers();
        								uList.stream().forEach(e->System.out.println(e.toString()));
        								System.out.print("\nEnter Ids of users that you want to share review with(End with 0): ");
        								sc.nextLine();
        								String userIds = sc.nextLine();
        								String[] arr = userIds.split(" ");
        								for (int i = 0; i < arr.length; i++) {
        									int UserIdInt = Integer.parseInt(arr[i]);
        									if (UserIdInt==0 || UserIdInt==signedInUser.getId()) {
//        										System.out.println("\u001B[91mYou can only share your own reviews\u001B[0m");
        										break;
        									}
        									else {
        										reviewDao.share(revId, UserIdInt);											
        										System.out.println("\u001B[92mReview shared Succesfully!\u001B[0m\n");
        									}
        								}
        							}
        							
        							break;
    							
        						case 10:
        							System.out.print("\033[H\033[2J");
            						System.out.flush();
            						Runtime.getRuntime().exec("clear");
        							System.out.print("Enter ID of review to be Deleted: ");
        							int dId = sc.nextInt();
        							uId = reviewDao.getReviewUserId(dId);
        							if (uId == signedInUser.getId()) {
        								reviewDao.delReview(dId, uId);
        								System.out.println("\u001B[92mReview Deleted Succesfully!\u001B[0m\n");
        								System.out.println();
									}
        							else {
        								System.out.println("\u001B[91mYou can only delete your own reviews\u001B[0m");
        								System.out.println();
									}
        							break;
        						default:
        							System.out.println("\u001B[91m***Invalid Choice***\u001B[0m");
        							break;
        						}
        					}
        	            	
        	            	break;            	
            			case 1:
            				User newUser = UserS.addNewUser(sc);
            				dao.addNewUser(newUser);
            				System.out.println("\u001B[32mNew user added successfully!\u001B[0m"
            						+ "\n");
                            break;
                                         
            			default:
            				System.out.println("\n"
            						+ "\u001B[91m***Invalid Choice***\u001B[0m"
            						+ "\n");
            				break;
            			}
            		} while (choice != 0);
                	sc.close();
            		
        		} catch (Exception e) {
        			e.printStackTrace();
        		}

        		
    		} catch (Exception e) {
    			e.printStackTrace();
    		}

    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	    	
    	    	
    	
    }

}
