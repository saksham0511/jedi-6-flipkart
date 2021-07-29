package com.flipkart.exception;

/**
 * Authorization Exception
 * @author JEDI-06
 *
 */

public class AuthorizationException extends Exception{
    public AuthorizationException(String message){
        super(message);
    }
}
