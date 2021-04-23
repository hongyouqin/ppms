package com.qhy.ppmsadmin.exception;

/**
 * 数据库操作异常
 */
public class DatabaseManipulationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DatabaseManipulationException(String message) {
        super(message);
    }
}
