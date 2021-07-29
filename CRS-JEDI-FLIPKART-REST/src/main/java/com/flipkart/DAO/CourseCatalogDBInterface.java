package com.flipkart.DAO;

import com.flipkart.bean.Course;

import java.util.List;

/**
 * @author JEDI-06
 * Interface for Course Catalog dao Operations
 *
 */

public interface CourseCatalogDBInterface {

    /**
     * This method is used to add a course in the course catalog
     * @param courseId
     * @param courseName
     * @return
     */
    public int addCoursesDB(int courseId, String courseName);

    /**
     * This method is used to remove a  course from the course catalog
     * @param courseId
     * @return Status of Course removal
     */
    public boolean removeCourseDB(int courseId);

    /**
     * This method is used to view all courses in the course catalog
     * @return List of courses
     */
    public List<Course> viewCatalogDB();

}
