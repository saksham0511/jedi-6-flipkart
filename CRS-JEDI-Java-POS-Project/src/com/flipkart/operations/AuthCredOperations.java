package com.flipkart.operations;

import com.flipkart.DAO.AuthCredDB;
import com.flipkart.DAO.AuthCredDBInterface;
import com.flipkart.bean.Student;
import com.flipkart.exception.AuthorizationException;

import java.util.ArrayList;
import java.util.List;

public class AuthCredOperations implements AuthCredInterface {
    AuthCredDBInterface authCredDBInterface;

    @Override
    public boolean authenticateUser(int userId, String password , Enum role) throws AuthorizationException{
        authCredDBInterface = new AuthCredDB();
        boolean status = authCredDBInterface.authenticateUserDB(userId, password, role);
        if(status == true){
            return status;
        }
        else{
            throw new AuthorizationException("Invalid User ID or Password");
        }
    }

    @Override
    public int registerStudent(Student student) {
        authCredDBInterface = new AuthCredDB();
        return authCredDBInterface.registerStudentDB(student);
    }
}
