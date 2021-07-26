package com.flipkart.application;

import com.flipkart.exception.*;

import java.util.Scanner;

/**
 * Entry point of crs application
 */

public class CRSApplicationMain {
    static String frameTop = "\n--------------------------------------------WELCOME TO COURSE REGISTRATION SYSTEM-------------------------------------------------------";
    static String frameBottom = "----------------------------------------------------------------------------------------------------------------------------------------";
    static public String space = "                                             ";
    static String option = space + "Option : ";

    /**
     * This method is used for login and sign up options
     * @throws CourseRemovalFailedException
     * @throws ProfessorAlreadyExistException
     * @throws CourseAlreadyExistException
     * @throws ApprovalFailedException
     * @throws ProfessorRemovalFailedException
     */

    public static void helper() throws CourseRemovalFailedException, ProfessorAlreadyExistException, CourseAlreadyExistException, ApprovalFailedException, ProfessorRemovalFailedException {
        System.out.println(frameTop);
        System.out.println(space + "Select: Login / Signup");
        System.out.print(option);
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        switch(choice){
            case "Login" : LoginAndSignupPage.login();
                break;
            case "Signup" : LoginAndSignupPage.signUp();
                break;
            default : System.out.println(space+"Invalid Option");
                break;
        }
    }

    /**
     * Main function
     * @param args
     * @throws CourseRemovalFailedException
     * @throws ProfessorAlreadyExistException
     * @throws CourseAlreadyExistException
     * @throws ApprovalFailedException
     * @throws ProfessorRemovalFailedException
     */
    public static void main(String[] args) throws CourseRemovalFailedException, ProfessorAlreadyExistException, CourseAlreadyExistException, ApprovalFailedException, ProfessorRemovalFailedException {
        helper();
    }
}
