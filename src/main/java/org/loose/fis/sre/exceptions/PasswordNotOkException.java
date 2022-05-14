package org.loose.fis.sre.exceptions;

public class PasswordNotOkException extends Exception{
    public PasswordNotOkException(){
        super("Password doesn't have at least 4 characters or one upper case letter!");
    }
}
