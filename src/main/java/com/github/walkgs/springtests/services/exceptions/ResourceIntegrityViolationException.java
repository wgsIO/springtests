package com.github.walkgs.springtests.services.exceptions;

import java.io.Serial;

public class ResourceIntegrityViolationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3466012693032159656L;

    public ResourceIntegrityViolationException(String message) {
        super(message);
    }

}
