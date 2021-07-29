package com.flipkart.bean;

import com.flipkart.constant.Role;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEDI-06
 * Student Class
 *
 */

public class Student extends User{
    /**
     * Default Constructor
     */
    public Student(){

    }

    private boolean isApproved;


    /**
     * Method to get Approval status of the Student
     * @return is Approved status
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     * Method to set Approval status of the Student
     * @param approved
     */
    public void setIsApproved(boolean approved) {
        isApproved = approved;
    }





    /**
     * Parameterized constructor
     * @param name
     * @param password
     * @param email
     * @param address
     */
    public Student( String name, String password,  String email, String address){
        super( Role.STUDENT,name, password, email, address);
        this.isApproved = false;
    }


}
