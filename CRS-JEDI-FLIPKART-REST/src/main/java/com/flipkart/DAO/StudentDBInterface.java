package com.flipkart.DAO;

import com.flipkart.bean.Course;

import java.util.List;

/**
 * @author JEDI-06
 * Interface for Student dao Operations
 *
 */

public interface StudentDBInterface {
    /**
     * This method is used by student to view the registered courses
     * @param studId
     * @return list of courses
     */
    public List<Course> getRegisteredCoursesDB(int studId);

    /**
     * This method is used by student to check if fee is paid or not
     * @param studentId
     * @return
     */
    public boolean isFeefeespaidDB(int studentId);
}
