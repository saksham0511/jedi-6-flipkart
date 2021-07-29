package com.flipkart.exception;

/**
 * Exception Course Removal Failed
 * @author JEDI-06
 *
 */

public class CourseRemovalFailedException extends Exception{
    public CourseRemovalFailedException(String message){
        super(message);
    }
}
