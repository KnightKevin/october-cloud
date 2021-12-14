package com.simon.oct.common.core.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CheckedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CheckedException(String msg) {
        super(msg);
    }

    public CheckedException(String msg, Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
