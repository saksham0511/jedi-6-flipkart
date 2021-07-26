package com.flipkart.constant;

/**
 * This enum is used to get the user role
 */

public enum Role {
    STUDENT,
    PROFESSOR,
    ADMIN;

    /**
     * This method is used to get role
     * @return Role name
     * */
    public String getRole() {
        return this.name();
    }

}