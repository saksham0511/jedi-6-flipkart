package com.flipkart.bean;

import com.flipkart.constant.Role;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEDI-06
 * Professor Class
 *
 */

public class Professor extends User{

    private List<Course> teachCourses ;

    /**
     * Default Constructor;
     */
    public Professor(){

    }
    /**
     * Method to get Courses taught by the Professor
     * @return Course List
     */
    public List<Course> getTeachCourses() {
        return teachCourses;
    }

    /**
     * Method to set Courses to be taught by the Professor
     * @param teachCourses
     */
    public void setTeachCourses(List<Course> teachCourses) {
        this.teachCourses = teachCourses;
    }

    /**
     * Parameterized constructor
     * @param professorId
     * @param name
     * @param password
     * @param email
     * @param address
     */
    public Professor(int professorId, String name, String password,String  email, String address) {
        super(Role.PROFESSOR,name, password, email, address);
        teachCourses = new ArrayList<Course>();

    }
}
