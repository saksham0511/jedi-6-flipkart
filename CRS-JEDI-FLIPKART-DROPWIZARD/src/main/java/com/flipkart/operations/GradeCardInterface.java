package com.flipkart.operations;

import com.flipkart.bean.Course;

import java.util.HashMap;

/**
 * @author JEDI-06
 * Interface for Grade Card Operations
 *
 */

public interface GradeCardInterface {

    /**
     * This method is used by student to view grade card
     * @param studId
     * @return Key-value pair of course and grade
     */
    public HashMap<Course, String> viewGrades(int studId);


}
