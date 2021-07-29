
package com.flipkart.restController;


        import com.flipkart.bean.Course;
        import com.flipkart.bean.Payment;
        import com.flipkart.bean.Professor;
        import com.flipkart.bean.Student;
        import com.flipkart.constant.BankEnum;
        import com.flipkart.constant.NotificationType;
        import com.flipkart.constant.PaymentModeEnum;
        import com.flipkart.exception.CourseRemovalFailedException;
        import com.flipkart.operations.*;
        import org.apache.log4j.Logger;

        import javax.ws.rs.*;
        import javax.ws.rs.Path;
        import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;
        import java.util.HashMap;
        import java.util.List;

@Path("/admin")
public class AdminRESTAPIController {
    AdminInterface adminOperation = new AdminOperations();
    CourseRegistrationInterface courseRegistrationInterface;
    StudentInterface studentInterface;
    private static final Logger logger = Logger.getLogger(String.valueOf(ProfessorRESTAPIController.class.getName()));

    /**
     * Method to display list courses available in course catalog
     */
    @GET
    @Path("/viewCatalog")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCatalog() {
        try {
            CourseCatalogInterface courseCatalog = new CourseCatalogOperations();
            return courseCatalog.viewCatalog();
        }
        catch(Exception e){
            logger.error("Exception occurred : " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to display list of unapproved students
     */
    @GET
    @Path("/viewUnapprovedStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewUnapprovedStudents() {
        try {
            AdminInterface adminOp = new AdminOperations();
            return adminOp.unApprovedStudent();
        }
        catch(Exception e){
            logger.error("Exception occurred : " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to display list of professors
     */
    @GET
    @Path("/viewProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<Course, Professor> viewProfessor() {
        try {
            AdminInterface adminOp = new AdminOperations();
            return adminOp.viewProfessor();
        }
        catch(Exception e){
            logger.error("Exception occurred : " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to remove a particular course from course catalog
     */
    @PUT
    @Path("/removeCourse/{courseCode}")
    public Response removeCourse(
            @PathParam("courseCode") int courseCode) throws CourseRemovalFailedException {
        try {
            AdminInterface adminOperation = new AdminOperations();
            adminOperation.removeCourse(courseCode);
            return Response.status(201).entity("Course with courseCode: " + courseCode + " deleted from catalog").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * Method to remove a particular professor from database
     */
    @PUT
    @Path("/removeProfessor/{professorCode}")
    public Response removeProfessor(
            @PathParam("professorCode") int professorCode) {
        try {
            AdminInterface adminOperation = new AdminOperations();
            adminOperation.removeProfessor(professorCode);
            return Response.status(201).entity("Professor with Professor Code: " + professorCode + " removed from the database.").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * Method to approve student
     */
    @PUT
    @Path("/approveStudent")
    public Response approveStudent(){
        try {
            AdminInterface adminOperation = new AdminOperations();
            adminOperation.approveStudent();
            return Response.status(201).entity("Students approved.").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * Method to add new course to course catalog
     */
    @POST
    @Path("/addNewCourse")
    @Consumes("application/json")
    public Response addNewCourse(Course course) {
        try {
            String courseName = course.getCourseName();
            int courseFees = course.getCourseFees();
            adminOperation.addNewCourse(courseName, courseFees);
            return Response.status(200).entity("Course Name : " + courseName + " added to catalog").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * Method to add new professor details
     */
    @POST
    @Path("/addProfessor")
    @Consumes("application/json")
    public Response addProfessor(Professor professor) {
        try {
            String name = professor.getName();
            String email = professor.getEmail();
            String address = professor.getAddress();
            String password = professor.getPassword();
            adminOperation.addProfessor(name, email, address, password);
            return Response.status(200).entity("Professor Name : " + name + " added to database").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }


    /**
     * Method to approve student fees
     */
    @POST
    @Path("/approveStudentFees")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudentFees(Payment payment){
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
                int mode = 2;
                if (mode == 2) {
                    PaymentModeEnum onlineMode = payment.getPaymentModeEnum();
                    BankEnum bank = payment.getBankEnum();
                    NotificationInterface notificationInterface = new NotificationOperations();
                    int status = notificationInterface.sendNotification(NotificationType.PAYMENT, payment.getStudentId(), onlineMode, bank, amount, null);
                    if (status != 0) {
                        return Response.status(201).entity("Student Approval Successful: Payment  ID:"+status).build();
                    } else {
                        return Response.status(201).entity("Student Approval Unsuccessful").build();
                    }
                }
            }
        } catch (Exception e){
            return Response.status(201).entity("Exception: "+e.getMessage()).build();
        }
        return null;
    }


}