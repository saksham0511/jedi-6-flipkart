package com.flipkart.application;

import com.flipkart.bean.Card;
import com.flipkart.bean.Course;
import com.flipkart.constant.BankEnum;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.PaymentModeEnum;
import com.flipkart.operations.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class StudentPage {
    static String frameTop = "\n--------------------------------------------WELCOME TO COURSE REGISTRATION SYSTEM-------------------------------------------------------";
    static String frameBottom = "----------------------------------------------------------------------------------------------------------------------------------------";
    static String space = "                                             ";
    static String option = space + "Option : ";
    static String exit = "--------------------------------------------------------------EXIT----------------------------------------------------------------------";
    StudentInterface studentOperations;
    CourseRegistrationInterface courseRegistrationOperations;
    GradeCardInterface gradeCardOperations;
    CourseCatalogInterface courseCatalogOperations;

    /**
     * This method is used to select from Student operations
     * @param studId
     */
    public void activity(int studId){

        studentOperations = new StudentOperations();

        courseRegistrationOperations = new CourseRegistrationOperations();
        gradeCardOperations = new GradeCardOperations();
        courseCatalogOperations = new CourseCatalogOperations();
        System.out.println(frameTop);
        while(true) {
            System.out.println(space + "select an option from below\n");
            System.out.println(space +
                    "1.Add course\n" +
                    space + "2.Drop course\n" +
                    space + "3.Course Registration\n" +
                    space + "4.View Grades\n" +
                    space + "5.Pay Fees\n" +
                    space + "6.View Registered Courses\n");
            Scanner sc = new Scanner(System.in);
            System.out.print(option);

            int choice = sc.nextInt();
            System.out.println(frameBottom);

            System.out.println(frameTop);
            switch (choice) {
                case 1:
                    addCourse(studId);
                    break;
                case 2:
                    dropCourse(studId);
                    break;
                case 3:
                    courseRegistration(studId);
                    break;
                case 4:
                    viewGrade(studId);
                    break;
                case 5:
                    makePayment(studId);
                    break;
                case 6:
                    viewRegisteredCourses(studId);
                    break;
                default:
                    System.out.println(space + "Invalid Action");
                    break;
            }
            System.out.print(space + "Do you want to continue as student(Y/N) : ");
            String Continue = sc.next();
            if (Continue.equals("N")) {
                System.out.println(exit);
                break;
            }
            System.out.println(frameBottom);
        }
    }

    /**
     * This method is used by Student for course registration
     * @param studId
     */
    private void courseRegistration(int studId){
        int count = 0;
        Scanner sc = new Scanner(System.in);
        CourseRegistrationInterface courseRegistrationInterface = new CourseRegistrationOperations();
        int check = courseRegistrationInterface.numOfRegisteredCourses(studId);
        if (check>=6){
            System.out.println(space+"Registration is Alredy Done");
            return;
        }
        while (count<6){
            System.out.print(space+"Enter Course Id: ");
            int courseId = sc.nextInt();
             int status = courseRegistrationInterface.addCourse(courseId,studId);
             if (status == 23){
                 System.out.println("Course is Already Added");
                 continue;
             }
             if(status == 1) {
                 System.out.println(space+"Course Added Successfully");
             }
             else{
                 System.out.println(space+"Incorrect Course Id");
                 count--;
             }
             count++;
        }
        System.out.println(space+"Course Registration Succesful");
    }

    /**
     * This method is used to add course for the student
     * @param studId
     */
    private void addCourse(int studId){
        StudentInterface studentInterface = new StudentOperations();
        boolean checkFeePaid = studentInterface.isFeefeespaidDB(studId);
        if (checkFeePaid){
            System.out.println(space+"Your Fees is Already Paid!. So you can't add course");
            return;
        }
        CourseRegistrationInterface courseRegistrationInterface = new CourseRegistrationOperations();
        int check = courseRegistrationInterface.numOfRegisteredCourses(studId);
        if (check==0){
            System.out.println(space+"Your Registration is Pending");
            return;
        }
        if (check>=6){
            System.out.println(space+"Course Limit Exceeded");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print(space+"Enter Course Id: ");
        int courseId = sc.nextInt();
        int status = courseRegistrationInterface.addCourse(courseId,studId);
        if(status == 1) {
            System.out.println(space+"Course Added Successfully");
        }
        else{
            System.out.println(space+"Incorrect Course Id");
        }
    }

    /**
     * This method is used to remove course for the student
     * @param studId
     */
    private void dropCourse(int studId){
        StudentInterface studentInterface = new StudentOperations();
        boolean checkFeePaid = studentInterface.isFeefeespaidDB(studId);
        if (checkFeePaid){
            System.out.println(space+"Your Fees is Already Paid!. So you Can't drop course");
            return;
        }
        CourseRegistrationInterface courseRegistrationInterface = new CourseRegistrationOperations();
        int check = courseRegistrationInterface.numOfRegisteredCourses(studId);
        if (check==0){
            System.out.println(space+"Registered Courses are zero. Can't be dropped");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print(space+"Enter Course Id: ");
        int courseId = sc.nextInt();
        boolean status = courseRegistrationInterface.removeCourse(courseId,studId);
        if (status){
            System.out.println(space+"Course Dropped Successfully");
        }
        else {
            System.out.println(space+"Course Not Dropped");
        }
    }

    /**
     * This method is used by student to view all the registered courses
     * @param studId
     */
    private void viewRegisteredCourses(int studId){
        CourseRegistrationInterface courseRegistrationInterface = new CourseRegistrationOperations();
        int check = courseRegistrationInterface.numOfRegisteredCourses(studId);
        if (check==0){
            System.out.println(space+"Complete your Registration");
            return;
        }
        StudentInterface studentInterface = new StudentOperations();
        List<Course> courseList = studentInterface.getRegisteredCourses(studId);
        System.out.println(space+"   Registered Courses   ");
        System.out.println(space+"--------------------------");
        System.out.println(space+"Course ID"+"     "+"Course Name");
        for (Course course:courseList){
            System.out.println(space+course.getCourseId()+"          "+course.getCourseName());
        }
    }

    /**
     * This method is used by student to view the grades
     * @param studId
     */
    private void viewGrade(int studId){
        CourseRegistrationInterface courseRegistrationInterface = new CourseRegistrationOperations();
        int check = courseRegistrationInterface.numOfRegisteredCourses(studId);
        if (check==0){
            System.out.println(space+"Complete your Registration");
            return;
        }
        GradeCardInterface gradeCardInterface = new GradeCardOperations();
        HashMap<Course,String> courseGrade = gradeCardInterface.viewGrades(studId);
        System.out.println(space+"                   Report Card");
        System.out.println(space+"---------------------------------------------------");

        System.out.println(space+"Course ID"+"     "+"Course Grade"+"             "+"Course Name");

        for(HashMap.Entry<Course, String> cg :courseGrade.entrySet()) {
                if (cg.getValue().equals("X")){
                        System.out.println(space+cg.getKey().getCourseId()+"          "+"Grade Not Assigned"+
                                "       "+cg.getKey().getCourseName());
                }
                else {
                    System.out.println(space+cg.getKey().getCourseId()+"          "+cg.getValue()+
                            "          "+cg.getKey().getCourseName());
                }
        }
    }

    /**
     * This method is used by student to make payment
     * @param studId
     */
    private void makePayment(int studId){
        CourseRegistrationInterface courseRegistrationInterface = new CourseRegistrationOperations();
        int check = courseRegistrationInterface.numOfRegisteredCourses(studId);
        if (check==0){
            System.out.println(space+"Complete your Registration");
            return;
        }
        StudentInterface studentInterface = new StudentOperations();
        boolean checkFeePaid = studentInterface.isFeefeespaidDB(studId);
        if (checkFeePaid){
            System.out.println(space+"Your Fees is Already Paid");
            return;
        }
        PaymentInterface paymentInterface = new PaymentOperations();
        int amount = paymentInterface.getPayment(studId);
        System.out.println(space+"Your Amount is: "+amount);
        System.out.print(space+"Do you want to continue (Y/N) : ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();
        if (choice.equals("Y")){
                System.out.println(space+"Payement Mode");
                System.out.println(space+"1.Online Mode");
                System.out.println(space+"2.Offline Mode");
                System.out.print(space+"Option:");
                int mode = sc.nextInt();
                if (mode == 1){
                    System.out.println(space+"Select Online Mode of Payment");
                    int index = 1;
                    for (PaymentModeEnum modeEnum: PaymentModeEnum.values()){
                        if (modeEnum.toString().equals("OFFLINE"))
                            continue;
                        System.out.println(space+index+"."+modeEnum);
                        index++;
                    }
                    System.out.print(space+"Option:");
                    int onlineModeChoice = sc.nextInt();
                    PaymentModeEnum onlineMode = PaymentModeEnum.getModeofPayment(onlineModeChoice);
                    if (onlineMode == null){
                        System.out.println(space+"Invalid Choice");
                        return;
                    }
                    System.out.println(space+"Select Bank");
                    index=1;
                    for (BankEnum bankEnum:BankEnum.values()){
                        if (bankEnum.toString().equals("CASH"))
                                continue;
                        System.out.println(space+index+"."+bankEnum);
                        index++;
                    }
                    System.out.print(space+"Option:");
                    int bankChoice  = sc.nextInt();
                    BankEnum bank = BankEnum.getBankName(bankChoice);
                    if (bank == null){
                        System.out.println(space+"Invalid Choice");
                        return;
                    }
                    System.out.print(space+"Enter Card Number:");
                    int cardNumber  = sc.nextInt();
                    System.out.print(space+"Enter Card Holder Name:");
                    String cardHolderName = sc.next();
                    System.out.print(space+"Enter CVV:");
                    int cvv = sc.nextInt();
                    Card card = new Card(cardNumber,cardHolderName);
                    NotificationInterface notificationInterface = new NotificationOperations();
                    int status = notificationInterface.sendNotification(NotificationType.PAYMENT,studId,onlineMode,bank,amount,card);
                    if (status != 0){
                        System.out.println(space+"Payment Successful");
                        System.out.println(space+"Your Payment ID: "+status);
                    }
                    else {
                        System.out.println(space+"Payment Unsuccessful");
                    }
                }
                else if (mode == 2){
                        System.out.println(space+"Pay your fees at Account Office and Admin will Approve it.!! Thanks");
                }
                else {
                    System.out.println(space+"Invalid Choice");
                }
        }
    }
}
