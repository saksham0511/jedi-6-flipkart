package com.flipkart.bean;

import com.flipkart.constant.Role;

/**
 *
 * @author JEDI-06
 * User Class
 *
 */

public class User {
    private int userId;
    private Role role;
    private String name;
    private String password;
    private String email;
    private String address;

    /**
     * Parameterized constructor
     * @param role
     * @param name
     * @param password
     * @param email
     * @param address
     */
    public User( Role role, String name, String password, String email, String address) {
        this.userId = userId;
        this.role = role;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    /**
     * Default Constructor
     */
    public User(){


    }



    /**
     * Method to get password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to set password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to get User Id
     * @return User Id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Method to set User Id
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Method to get Role
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Method to set Role
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Method to get Name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get Email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to set Email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get Address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method to set Address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
