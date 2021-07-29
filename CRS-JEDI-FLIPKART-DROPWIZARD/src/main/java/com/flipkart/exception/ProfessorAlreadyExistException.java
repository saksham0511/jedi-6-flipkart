package com.flipkart.exception;

/**
 * Exception Professor already exists
 * @author JEDI-06
 *
 */

public class ProfessorAlreadyExistException extends Exception{
    public ProfessorAlreadyExistException (String message){
        super(message);
    }
}
