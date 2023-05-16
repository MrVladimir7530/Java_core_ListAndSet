package com.example.listandset.exceptions;

public class EmployeeAlreadyAddedException extends Exception{
    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }

    public EmployeeAlreadyAddedException(String message, Throwable cause) {
        super(message, cause);
    }
}
