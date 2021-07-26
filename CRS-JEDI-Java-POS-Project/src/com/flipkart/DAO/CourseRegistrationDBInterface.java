package com.flipkart.DAO;

import com.flipkart.bean.Course;

/**
 * @author JEDI-06
 * Interface for Course Registration dao Operations
 *
 */

public interface CourseRegistrationDBInterface {

    /**
     * This method is used to add a course in the Student's course list
     * @param courseId
     * @param studId
     * @return
     */
    public int addCourseDB(int courseId,int studId);

    /**
     * This method is used to remove a course from the Student's course list
     * @param courseId
     * @param studId
     * @return
     */
    public boolean removeCourseDB(int courseId,int studId);

    /**
     * This method is used to view all the courses in which a student is registered
     * @param userId
     * @return
     */
    public Course[] registeredCourseDB(int userId);

    /**
     * This method is used to make payment for a course
     * @param courseId
     * @return
     */
    public int makePaymentDB(int courseId);

    /**
     * This method is used to get a count of courses in which a student is registered
     * @param studId
     * @return
     */
    public int numOfRegisteredCourses(int studId);
}
