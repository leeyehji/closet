package com.closet.board.exception;

// 403 Forbidden
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
