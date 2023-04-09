package com.digitalsettings.tms.util;

import com.digitalsettings.tms.exception.HandledException;
import com.digitalsettings.tms.exception.detail.HandledExceptionDetail;
import org.springframework.http.HttpStatus;

public final class HandledExceptionFactory {

    public static HandledException getBadRequestException(String message) {
        return getHandledException(HttpStatus.BAD_REQUEST, message);
    }

    public static HandledException getNotFoundException(String message) {
        return getHandledException(HttpStatus.NOT_FOUND, message);
    }

    public static HandledException getHandledException(HttpStatus status, String message) {
        return new HandledException(status, new HandledExceptionDetail(message));
    }

    public static HandledException getUnauthorizedException(String message) {
        return new HandledException(HttpStatus.UNAUTHORIZED, new HandledExceptionDetail(message));
    }

    public static HandledException getForbiddenException(String message) {
        return new HandledException(HttpStatus.FORBIDDEN, new HandledExceptionDetail(message));
    }
}
