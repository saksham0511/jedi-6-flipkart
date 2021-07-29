package com.flipkart.restController;

import com.flipkart.bean.Card;
import com.flipkart.bean.Course;
import com.flipkart.constant.BankEnum;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.PaymentModeEnum;
import com.flipkart.operations.*;
import com.flipkart.bean.CourseStudent;
import com.flipkart.bean.Payment;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author JEDI-06
 * StudentRESTAPIController Class
 */
@Path("/student")
public class StudentRESTAPIController {

    StudentInterface studentInterface;
    CourseRegistrationInterface courseRegistrationInterface;
    private static Logger logger = Logger.getLogger(StudentRESTAPIController.class);

    /**
     * Method to add new Course
     * @param courseStudent
     * @return Response Status
     */
    @POST
    @Path("/addCourse")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(CourseStudent courseStudent) {
        //  client --- service ---- dao ----> SQL
        try {
            studentInterface = new StudentOperations();
            boolean checkFeePaid = studentInterface.isFeefeespaidDB(courseStudent.getStudentId());
            if (checkFeePaid){
                return Response.status(201).entity("Your Fees is Already Paid!. So you can't add course").build();
            }
            courseRegistrationInterface = new CourseRegistrationOperations();
            int check = courseRegistrationInterface.numOfRegisteredCourses(courseStudent.getStudentId());
            if (check==0){
                return Response.status(201).entity("Your Registration is Pending").build();
            }
            if (check>=6){
                return Response.status(201).entity("Course Limit Exceeded").build();
            }
            int status = courseRegistrationInterface.addCourse(courseStudent.getCourseID(),courseStudent.getStudentId());
            if(status == 1) {
                return Response.status(201).entity("Successfully Added course").build();

            }
            else{
                return Response.status(500).entity("Unsuccessful").build();
            }
        } catch (Exception e) {
            logger.error("Could not view enrolled students list, exception occurred with message: "+e.getMessage());
        }

        return null;

    }


    /**
     * Method for Course Registration of Student
     * @param courseStudents
     * @return Response status
     */
    @POST
    @Path("/courseRegistration")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response courseRegistration(CourseStudent[] courseStudents){
        try {
            int count = 0;
            Scanner sc = new Scanner(System.in);
            CourseRegistrationInterface courseRegistrationInterface = new CourseRegistrationOperations();
            int check = courseRegistrationInterface.numOfRegisteredCourses(courseStudents[0].getStudentId());
            if (check>=6){
                return Response.status(201).entity("Registration is Already done").build();
            }
            while (count<6){
                System.out.print("Enter Course Id: ");
                int status = courseRegistrationInterface.addCourse(courseStudents[count].getCourseID(),courseStudents[count].getStudentId());
                if (status == 23){

                    System.out.println("Course is Already Added");
                    continue;
                }
                if(status == 1) {
                    System.out.println("Course Added Successfully");
                }
                else{
                    System.out.println("Incorrect Course Id");
                    count--;
                }
                count++;
            }
            return Response.status(201).entity("Course Registration Succesful").build();
        }catch (Exception exception){
            logger.error("Registration Not successful: "+exception.getMessage());
            return Response.status(500).entity("Course Registration Not Succesful").build();
        }
    }


    /**
     *Method for Fees Payment
     * @param payment
     * @return Response Status
     */
    @POST
    @Path("/makePayment")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makePayment(Payment payment) {
        try {
        courseRegistrationInterface = new CourseRegistrationOperations();
        int check = courseRegistrationInterface.numOfRegisteredCourses(payment.getStudentId());
        if (check == 0) {
            return Response.status(201).entity("Complete your Registration").build();
        }
        studentInterface = new StudentOperations();
        boolean checkFeePaid = studentInterface.isFeefeespaidDB(payment.getStudentId());
        if (checkFeePaid) {
            return Response.status(201).entity("Your Fees is Already Paid").build();
        }
        PaymentInterface paymentInterface = new PaymentOperations();
        int amount = paymentInterface.getPayment(payment.getStudentId());

        String choice = "Y";
        if (choice.equals("Y")) {
            int mode = 1;
            if (mode == 1) {
                PaymentModeEnum onlineMode = payment.getPaymentModeEnum();
                BankEnum bank = payment.getBankEnum();
                Card card = new Card(payment.getCard().getCardNumber(), payment.getCard().getCardHolderName());
                NotificationInterface notificationInterface = new NotificationOperations();
                int status = notificationInterface.sendNotification(NotificationType.PAYMENT, payment.getStudentId(), onlineMode, bank, amount, card);
                if (status != 0) {
                    return Response.status(201).entity("Payment Successful: Payment  ID:"+status).build();
                } else {
                    return Response.status(201).entity("Payment Unsuccessful").build();
                }
            }
        }
       } catch (Exception e){
            return Response.status(500).entity("Exception: "+e.getMessage()).build();
        }
        return null;
    }

    /**
     * Method to drop course
     * @param studId
     * @param courseId
     * @return Response status
     */

    @DELETE
    @Path("/dropCourse/{studentId}/{courseId}")
    public Response dropCourse(@PathParam("studentId") int studId,@PathParam("courseId") int courseId){
        try {
            studentInterface = new StudentOperations();
            boolean checkFeePaid = studentInterface.isFeefeespaidDB(studId);
            if (checkFeePaid){
                System.out.println("Your Fees is Already Paid!. So you Can't drop course");
                return Response.status(201).entity("Your Fees is Already Paid!. So you Can't drop course").build();
            }
            CourseRegistrationInterface courseRegistrationInterface = new CourseRegistrationOperations();
            int check = courseRegistrationInterface.numOfRegisteredCourses(studId);
            if (check==0){
                System.out.println("Registered Courses are zero. Can't be dropped");
                return Response.status(201).entity("Registered Courses are zero. Can't be dropped").build();
            }

            boolean status = courseRegistrationInterface.removeCourse(courseId,studId);
            if (status){
                return Response.status(201).entity("Course Dropped Successfully").build();
            }
            else {
                return Response.status(201).entity("Course Not Dropped").build();
            }

        }catch (Exception e){
                Response.status(500).entity("Exception: "+e.getMessage()).build();
        }
        return null;
    }


    /**
     * Mwthod to view Registered Courses
     * @param studentId
     * @return List of courses registered by Student
     */
    @GET
    @Path("/viewRegisteredCourses/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewRegisteredCourses(@PathParam("studentId") int studentId){
        try{
            courseRegistrationInterface = new CourseRegistrationOperations();
            int check = courseRegistrationInterface.numOfRegisteredCourses(studentId);
            if (check==0){
                logger.info("Complete your Registration");
                return null;
            }
            StudentInterface studentInterface = new StudentOperations();
            List<Course> courseList = studentInterface.getRegisteredCourses(studentId);
            return courseList;
        }catch (Exception exception){
            logger.error(exception.getMessage());
        }

        return null;
    }


    /**
     * Method to view grades
     * @param studId
     * @return student grades list with Course Id
     */
    @GET
    @Path("/viewGrades/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<Course,String> viewGrades(@PathParam("studentId") int studId){
        try {
            courseRegistrationInterface = new CourseRegistrationOperations();
            int check = courseRegistrationInterface.numOfRegisteredCourses(studId);
            if (check==0){
                logger.info("Complete your Registration");
                return null;
            }
            GradeCardInterface gradeCardInterface = new GradeCardOperations();
            HashMap<Course,String> courseGrade = gradeCardInterface.viewGrades(studId);

            return courseGrade;

        }catch (Exception exception){
            logger.error(exception.getMessage());
        }
        return null;
    }
}
