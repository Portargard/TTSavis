package com.example.TTWebBanHang.Exceptions;


public class AllServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public AllServiceException(String message)
    {
        super(message);
    }
}
