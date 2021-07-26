package com.flipkart.bean;

import com.flipkart.constant.Role;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEDI-06
 * Student Class
 *
 */

public class Student extends User{
    /**
     * Default Constructor
     */
    public Student(){

    }
    private boolean isApproved;
    private List<Course> enrolledCourses;

    /**
     * Method to get Approval status of the Student
     * @return is Approved status
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     * Method to set Approval status of the Student
     * @param approved
     */
    public void setIsApproved(boolean approved) {
        isApproved = approved;
    }

    /**
     * Method to get list of courses in which a student is enrolled
     * @return List of Courses
     */
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    /**
     * Method to set course enrollment for student
     * @param enrolledCourses
     */
    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    /**
     * Parameterized constructor
     * @param name
     * @param password
     * @param email
     * @param address
     */
    public Student( String name, String password,  String email, String address){
        super( Role.STUDENT,name, password, email, address);
        this.isApproved = false;
        enrolledCourses = new ArrayList<>();
    }


}
