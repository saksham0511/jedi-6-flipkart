package com.flipkart.operations;
import com.flipkart.bean.Student;
import com.flipkart.bean.Course;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.ProfessorAlreadyExistException;

import java.sql.SQLException;
import java.util.List;

/**
 * @author JEDI-06
 * Interface for Professor Operations
 *
 */

public interface ProfessorInterface {

    /**
     * This method is used to allot a course to Professor
     * @param profId
     * @param courseId
     * @return status
     */
    public boolean teachCourse(int profId, int courseId);

    /**
     * This method is used to get a list of enrolled students in a particular course
     * @param courseId
     * @return list of students
     */
    public List<Student> getEnrolledStudents(int courseId);



    /**
     * This method is by Professor to give grades to student
     * @param studId
     * @param courseId
     * @param grade
     * @throws GradeNotAddedException
     * @return status
     */
    public boolean addGrades(int studId, int courseId, String grade) throws GradeNotAddedException;
}
