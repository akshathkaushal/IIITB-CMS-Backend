package com.spe.iiitbcms.exceptions;

public class CMSException extends RuntimeException {
    public CMSException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public CMSException(String exMessage) {
        super(exMessage);
    }
}