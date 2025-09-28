package com.closet.board.exception;

// 400 Bad Request
// TODO: global 위치로 바꾸어서 모든 패키지에서 사용 가능한 커스텀 예외처리 기능으로 바꾸는 것이 나을 지도.
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
