package com.flipkart.operations;


/**
 * @author JEDI-06
 * Interface for Course Registration Operations
 *
 */

public interface CourseRegistrationInterface {

    /**
     * This method is used to add a course in the Student's course list
     * @param courseId
     * @param studId
     * @return status
     */
    public int addCourse(int courseId,int studId);

    /**
     * This method is used to remove a course from the Student's course list
     * @param courseId
     * @param studId
     * @return status
     */
    public boolean removeCourse(int courseId,int studId);


    /**
     * This method is used to get a count of courses in which a student is registered
     * @param studId
     * @return status
     */
    public int numOfRegisteredCourses(int studId);
}
