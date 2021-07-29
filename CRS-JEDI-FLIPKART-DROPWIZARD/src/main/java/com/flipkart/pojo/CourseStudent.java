package com.flipkart.bean;

/**
 *
 * @author JEDI-06
 * CourseStudent Class
 */

public class CourseStudent {

   private int studentId;
   private int courseId;

    /**
     * Method to get Student Id
     * @return
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Method to set Student Id
     * @param studentId
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Method to get Course Id
     * @return
     */
    public int getCourseID() {
        return courseId;
    }

    /**
     * Method to set Course Id
     * @param courseId
     */
    public void setCourseID(int courseId) {
        this.courseId = courseId;
    }
}
