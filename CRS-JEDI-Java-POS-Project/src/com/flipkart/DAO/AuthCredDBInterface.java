package com.flipkart.DAO;

import com.flipkart.bean.Student;

/**
 * @author JEDI-06
 * Interface for Author Credentials dao operations
 *
 */

public interface AuthCredDBInterface {

    /**
     * This method is used to authenticate the user
     * @param userId
     * @param password
     * @param role
     * @return
     */
    public boolean authenticateUserDB(int userId, String password, Enum role);

    /**
     * This method is used for registration of the student
     * @param student
     * @return
     */
    public int registerStudentDB(Student student);
}
