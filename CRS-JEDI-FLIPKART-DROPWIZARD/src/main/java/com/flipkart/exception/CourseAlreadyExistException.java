package com.flipkart.exception;

/**
 * Exception Course Already Exists
 * @author JEDI-06
 *
 */

public class CourseAlreadyExistException extends Exception{
    public CourseAlreadyExistException(String message){
        super(message);
    }
}
