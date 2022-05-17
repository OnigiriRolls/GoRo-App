package org.loose.fis.sre.exceptions;

public class UserDoesNotExistException extends Exception{
    public UserDoesNotExistException() {
        super("User does not exist! You have to register!");
    }
}
