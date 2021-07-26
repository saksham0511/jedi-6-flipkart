package com.flipkart.application;

import com.flipkart.bean.Student;
import com.flipkart.constant.Role;
import com.flipkart.exception.*;
import com.flipkart.operations.AdminInterface;
import com.flipkart.operations.AdminOperations;
import com.flipkart.operations.AuthCredInterface;
import com.flipkart.operations.AuthCredOperations;

import javax.naming.AuthenticationException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoginAndSignupPage {

    static String frameTop = "\n--------------------------------------------WELCOME TO COURSE REGISTRATION SYSTEM-------------------------------------------------------";
    static String frameBottom = "----------------------------------------------------------------------------------------------------------------------------------------";
    static String space = "                                             ";
    static String option = space + "Option : ";
    static String exit = "--------------------------------------------------------------EXIT----------------------------------------------------------------------";

    /**
     * This method is used for user login
     * @throws CourseRemovalFailedException
     * @throws ProfessorAlreadyExistException
     * @throws CourseAlreadyExistException
     * @throws ApprovalFailedException
     * @throws ProfessorRemovalFailedException
     */
    public static void login() throws CourseRemovalFailedException, ProfessorAlreadyExistException, CourseAlreadyExistException, ApprovalFailedException, ProfessorRemovalFailedException {
        Scanner sc = new Scanner(System.in);
        System.out.println(frameTop);
        while (true){

            System.out.println(space + "What is your role?");
            System.out.println(space + "  1.Student\n" +
                    space + "  2.Professor\n" +
                    space + "  3.Admin");
            System.out.print(option);

            int choice = sc.nextInt();
            AuthCredInterface authCredInterface = new AuthCredOperations();
            int userId=0;
            String password;
            boolean isAuthenticated = false;
            switch (choice){
                case 1:
                    System.out.print(space + "Enter UserId : ");
                    userId = sc.nextInt();

                    System.out.print(space + "Enter Password : ");
                    password = sc.next();
                    try {
                        isAuthenticated = authCredInterface.authenticateUser(userId, password, Role.STUDENT);
                    }
                    catch(Exception e){
                        System.out.println("Authentication failed with error : " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print(space + "Enter UserId : ");
                    userId = sc.nextInt();
                    System.out.print(space + "Enter Password : ");
                    password = sc.next();
                    try {
                        isAuthenticated = authCredInterface.authenticateUser(userId, password, Role.PROFESSOR);
                    }
                    catch(Exception e){
                        System.out.println("Authentication failed with error : " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print(space + "Enter UserId : ");
                    userId = sc.nextInt();
                    System.out.print(space + "Enter Password : ");
                    password = sc.next();
                    if(userId == 4001 && password.equals("admin")){
                        isAuthenticated = true;
                    }
                    else{
                        System.out.println(space+"Invalid Credentials");
                    }
                    break;
                default : System.out.println(space+"Invalid role");

            }
            if(isAuthenticated) {
                StudentPage studentPage;
                ProfessorPage professorPage;
                AdminPage adminPage;
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                LocalDateTime myDateObj = LocalDateTime.now();

                String formattedDate = myDateObj.format(myFormatObj);
                switch (choice) {
                    case 1:
                        AdminInterface adminInterface = new AdminOperations();
                        boolean isApproved = false;
                        isApproved = adminInterface.isApproved(userId);
                        if (isApproved) {
                            System.out.println(space + formattedDate + " Login Successful");
                            studentPage = new StudentPage();
                            studentPage.activity(userId);
                        } else {
                            System.out.println(space + "Student not approved");
                        }
                        break;
                    case 2:
                        professorPage = new ProfessorPage();
                        try {
                            System.out.println(space + formattedDate + " Login Successful");
                            professorPage.activity(userId);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        break;
                    case 3:
                        System.out.println(space + formattedDate + " Login Successful");
                        adminPage = new AdminPage();
                        adminPage.activity();
                        break;
                    default:
                        System.out.println("Invalid role");
                }
                System.out.print(space + "Do you want to continue to login(Y/N) : ");
                String Continue = sc.next();
                if (Continue.equals("N")) {
                    System.out.println(exit);
                    break;
                }
            }
        }
    }

    /**
     * This method is used for user registration
     * @throws CourseRemovalFailedException
     * @throws ProfessorAlreadyExistException
     * @throws CourseAlreadyExistException
     * @throws ApprovalFailedException
     * @throws ProfessorRemovalFailedException
     */
    public static void signUp() throws CourseRemovalFailedException, ProfessorAlreadyExistException, CourseAlreadyExistException, ApprovalFailedException, ProfessorRemovalFailedException {
        Scanner sc = new Scanner(System.in);
        System.out.print(space + "Are you a student? Y/N " );
        String op = sc.next();
        if(op.equals("Y")){

            System.out.print(space + "Enter your name : ");
            String userName = sc.next();
            System.out.print(space + "Enter your email ID : ");
            String emailId = sc.next();
            System.out.print(space + "Enter your address : ");
            String address = sc.next();
            System.out.print(space + "Enter your password : ");
            String password = sc.next();
            System.out.print(space + "Re-enter password : ");
            String confirmPassword = sc.next();
            if(password.equals(confirmPassword)){

                AuthCredInterface authCredInterface = new AuthCredOperations();
                Student student = new Student(userName, password, emailId, address);
                int status = authCredInterface.registerStudent(student);
                if(status == 0){
                    System.out.println(space + "Student Signup is unsuccessful");
                }
                else {
                    System.out.println(space + "Student Signup successful");
                }

                CRSApplicationMain.helper();


            }
            else{
                System.out.println(space + "Your password doesnot match");
                System.out.print(space + "Do you want to sign up again : Y/N ");
                String choice = sc.next();
                if(choice.equals("Y")){
                    signUp();
                }
                else{
                    System.out.println(space + "Thank You! Visit Again :)");
                }
            }

        }
        else{
            System.out.println(space + "Sign up is only for a student");
        }

    };
}
