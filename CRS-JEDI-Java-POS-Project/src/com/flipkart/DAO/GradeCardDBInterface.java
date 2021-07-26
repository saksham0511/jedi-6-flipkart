package com.flipkart.DAO;

import com.flipkart.bean.Course;

import java.util.HashMap;

/**
 * @author JEDI-06
 * Interface for Grade Card dao Operations
 *
 */

public interface GradeCardDBInterface {
    /**
     * This method is used by student to view grade card
     * @param studId
     * @return Key-value pair of course and grade
     */
    public HashMap<Course, String> viewGradesDB(int studId);

    /**
     * This method is used to update grades of a student
     * @param studId
     * @param courseId
     * @param grade
     * @return
     */
    public boolean updateReportCardDB(int studId, int courseId, String grade);
}
