package com.flipkart.operations;

import com.flipkart.bean.Student;
import com.flipkart.exception.AuthorizationException;

/**
 * @author JEDI-06
 * Interface for Author Credentials operations
 *
 */

public interface AuthCredInterface {

    /**
     * This method is used to authenticate the user
     * @param userId
     * @param password
     * @param role
     * @return status
     */
    public boolean authenticateUser(int userId, String password, Enum role) throws AuthorizationException;

    /**
     * This method is used for registration of the student
     * @param student
     * @return status
     */
    public int registerStudent(Student student);

}