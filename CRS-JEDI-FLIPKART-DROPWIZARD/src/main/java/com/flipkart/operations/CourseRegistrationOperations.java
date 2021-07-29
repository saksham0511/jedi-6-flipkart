package com.flipkart.operations;

import com.flipkart.DAO.CourseRegistrationDB;
import com.flipkart.DAO.CourseRegistrationDBInterface;
import com.flipkart.bean.Course;

public class CourseRegistrationOperations implements  CourseRegistrationInterface{

    CourseRegistrationDBInterface courseRegistrationDBInterface;
    @Override
    public int addCourse(int courseId,int studId) {
        courseRegistrationDBInterface = new CourseRegistrationDB();
        int status = courseRegistrationDBInterface.addCourseDB(courseId,studId);
        return status;
    }

    @Override
    public boolean removeCourse(int courseId,int studId) {
        courseRegistrationDBInterface = new CourseRegistrationDB();
        return courseRegistrationDBInterface.removeCourseDB(courseId,studId);
        //return false;
    }



    @Override
    public int numOfRegisteredCourses(int studId) {
        courseRegistrationDBInterface = new CourseRegistrationDB();
        int check = courseRegistrationDBInterface.numOfRegisteredCourses(studId);
        return check;
    }
}
