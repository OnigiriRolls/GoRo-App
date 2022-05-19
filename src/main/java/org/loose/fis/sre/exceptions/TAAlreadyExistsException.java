package org.loose.fis.sre.exceptions;

public class TAAlreadyExistsException extends Exception {
    public TAAlreadyExistsException(){
        super("Tourist Attraction already exists! Create a new one!");
    }
}
