package com.flipkart.operations;
import com.flipkart.bean.Course;

import java.util.List;

/**
 * @author JEDI-06
 * Interface for Student Operations
 *
 */

public interface StudentInterface {
    /**
     * This method is used by student to view the registered courses
     * @param studId
     * @return list of courses
     */
    public List<Course> getRegisteredCourses(int studId);

    /**
     * This method is used by student to check if fee is paid or not
     * @param studentId
     * @return status
     */
    public boolean isFeefeespaidDB(int studentId);
}
