package com.flipkart.DAO;

import com.flipkart.bean.Student;
import com.flipkart.exception.ProfessorAlreadyExistException;

import java.sql.SQLException;
import java.util.List;

/**
 * @author JEDI-06
 * Interface for Professor dao Operations
 *
 */

public interface ProfessorDBInterface {

    /**
     * This method is used to allot a course to Professor
     * @param profId
     * @param courseId
     * @return
     */
    public boolean teachCourseDB(int profId, int courseId);

    /**
     * This method is used to get a list of enrolled students in a particular course
     * @param courseId
     * @return list of students
     */
    public List<Student> getEnrolledStudentsDB(int courseId);

    /**
     * This method is by Professor to give grades to student
     * @param studId
     * @param courseId
     * @param grade
     * @return
     */
    public boolean addGradesDB(int studId, int courseId, String grade);

}
