package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEDI-06
 * Course Class
 *
 */

public class Course {
    private int courseId;
    private String courseName;
    private int professorId;
    private boolean isAvailable;
    private List<Student> studentList;


    /**
     * Default Constructor
     */
    public Course() {

    }

    /**
     * Parameterized constructor
     * @param courseId
     * @param courseName
     */
    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.professorId = -1; //-1 is for no professor is allocated
        this.isAvailable = true;
        this.studentList = new ArrayList<Student>();
    }

    /**
     * Method to get Course Id
     * @return Course Id
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Method to set Course Id
     * @param courseId
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Method to get Course Name
     * @return Course Name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Method to set Course Name
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Method to get Professor Id
     * @return Professor Id
     */
    public int getProfessorId() {
        return professorId;
    }

    /**
     * Method to set Professor Id
     * @param professorId
     */
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    /**
     * Method to check course availability
     * @return is Available status
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Method to set course availability
     * @param available
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Method to get list of Students
     * @return Student list
     */
    public List<Student> getStudentList() {
        return studentList;
    }

    /**
     * Method to set Student list
     * @param studentList
     */
    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }


}
