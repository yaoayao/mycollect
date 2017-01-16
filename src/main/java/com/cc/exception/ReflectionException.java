package com.cc.exception;


import org.omg.CORBA.CompletionStatus;

import java.io.Serializable;

/**
 * Created by yaoyao on 2017/1/16 10:28.
 */
public class ReflectionException extends SystemException implements Serializable{

    private static final long serialVersionUID = 6394499025176972623L;

    private static final String MESSAGE = "Reflection fail, the reason is: %s";

    public ReflectionException(final String reason) {
        super(MESSAGE, reason);
    }

    /**
     * Constructs a <code>SystemException</code> exception with the specified detail
     * message, minor code, and completion status.
     * A detail message is a String that describes this particular exception.
     *
     * @param reason    the String containing a detail message
     * @param minor     the minor code
     * @param completed the completion status
     */
    protected ReflectionException(String reason, int minor, CompletionStatus completed) {
        super(reason, minor, completed);
    }
}
