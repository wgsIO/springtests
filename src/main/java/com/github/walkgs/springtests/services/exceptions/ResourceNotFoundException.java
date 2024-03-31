package com.github.walkgs.springtests.services.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5311841374579047519L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
