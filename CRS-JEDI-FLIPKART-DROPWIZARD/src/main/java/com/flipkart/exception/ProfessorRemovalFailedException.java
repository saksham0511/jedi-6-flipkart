package com.flipkart.exception;

/**
 * Exception Professor removal failed
 * @author JEDI-06
 *
 */

public class ProfessorRemovalFailedException extends Exception {
    public ProfessorRemovalFailedException(String message){
        super(message);
    }
}
