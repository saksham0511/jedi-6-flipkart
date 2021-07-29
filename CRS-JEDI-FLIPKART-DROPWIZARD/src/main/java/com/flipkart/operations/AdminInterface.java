package com.flipkart.operations;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author JEDI-06
 * Interface for Admin Operations
 *
 */

public interface AdminInterface {
    /**
     * This method is used by admin to approve the registration of student
     * @throws ApprovalFailedException
     * @return Status of Approval done by the admin
     */
    public boolean approveStudent() throws ApprovalFailedException;

    /**
     * This method is used by admin to remove the existing professor
     * @param profId
     * @throws ProfessorRemovalFailedException
     * @return Removal status of the professor
     */
    public boolean removeProfessor(int profId) throws ProfessorRemovalFailedException;

    /**
     * This method is used by admin to add professor
     * @param name
     * @param email
     * @param address
     * @param Password
     * @throws ProfessorAlreadyExistException
     * @return status
     */
    public int addProfessor(String name, String email, String address, String Password) throws ProfessorAlreadyExistException;

    /**
     * This method is used by admin to add a new course
     * @param courseName
     * @param fees
     * @throws CourseAlreadyExistException
     * @return status
     */
    public int addNewCourse(String courseName,int fees) throws CourseAlreadyExistException;

    /**
     * This method is used by admin to remove a course
     * @param courseId
     * @throws CourseRemovalFailedException
     * @return Removal status of the course
     */
    public boolean removeCourse(int courseId) throws CourseRemovalFailedException;

    /**
     * This method is used to get a list of unapproved students
     * @return List of unapproved students
     */
    public List<Student> unApprovedStudent();

    /**
     * This method is used to check if the student is approved by the admin or not
     * @param userId
     * @return Approval status of the student
     */
    public boolean isApproved(int userId);

    public HashMap<Course, Professor> viewProfessor();

}
