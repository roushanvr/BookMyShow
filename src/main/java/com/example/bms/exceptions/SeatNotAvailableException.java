package com.example.bms.exceptions;

public class SeatNotAvailableException extends Exception{
    public SeatNotAvailableException(){
        super("seats not available");
    }
}
