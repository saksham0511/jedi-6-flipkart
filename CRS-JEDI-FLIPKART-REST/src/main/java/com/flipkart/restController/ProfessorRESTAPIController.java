package com.flipkart.restController;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseRemovalFailedException;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.operations.*;
import com.flipkart.pojo.StudentPojo;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/professor")
public class ProfessorRESTAPIController {

    ProfessorInterface professorOperations;
    CourseCatalogInterface courseCatalogOperations;
    private static final Logger logger = Logger.getLogger(String.valueOf(ProfessorRESTAPIController.class.getName()));

    /**
     * This method is used to view list of enrolled students
     * @param courseId
     */
    @GET
    @Path("/viewEnrolledStudents/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentPojo> viewEnrolledStudents(@PathParam("courseId") int courseId) {

        //  client --- service ---- dao ----> SQL
        System.out.println("request received");
        List<StudentPojo> studentList = new ArrayList<>();
        try {
            professorOperations = new ProfessorOperations();

            studentList = professorOperations.getEnrolledStudents(courseId);


        } catch (Exception e) {
            logger.error("Could not view enrolled students list, exception occurred with message : " + e.getMessage());
        }
        return studentList;


    }

    /**
     * This method is used by Professor to view Course Catalog

     */
    @GET
    @Path("/viewCatalog")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCatalog() {

        //  client --- service ---- dao ----> SQL

        System.out.println("in catalog");
        List<Course> courseList = new ArrayList<>();
        try {
            courseCatalogOperations = new CourseCatalogOperations();
            courseList = courseCatalogOperations.viewCatalog();

        } catch (Exception e) {
            logger.error("SQL Exception occurred with message" + e.getMessage());
        }
        return courseList;
    }

    /**
     * This method is used by Professor to select course to teach
     * @param profId
     * @param courseId
     */
    @PUT
    @Path("/teachCourse")


    public Response teachCourse(@QueryParam("courseId") int courseId, @QueryParam("profId") int profId){
        try {
            professorOperations = new ProfessorOperations();
            boolean status = professorOperations.teachCourse(profId, courseId);
            if (status ) {
                return Response.status(201).entity( "Course allotment is successful").build();
            }
            else{
                return Response.status(500).entity("Could not add a course to teach").build();
            }
        }
        catch(Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
    }





    /**
     * This method is used by Professor to give grades to student
     * @param studentId
     * @param courseId
     * @param grade
     */
    @PUT
    @Path("/addGrades")
    public Response addGrades(@QueryParam("courseId") int courseId, @QueryParam("studentId") int studentId, @QueryParam("grade") String grade){

        boolean gradeStatus = false;
        try{
            System.out.println("request received" + courseId + " "+ studentId + " "+grade);
            professorOperations = new ProfessorOperations();
            gradeStatus = professorOperations.addGrades(studentId, courseId, grade);
            if (gradeStatus == false) {
                return Response.status(500).entity("Grade not added").build();

            } else {
                return Response.status(201).entity( "Grade added succesfully").build();

            }
        }
        catch(GradeNotAddedException e){
            return Response.status(500).entity(e.getMessage()).build();
        }
    }


}
