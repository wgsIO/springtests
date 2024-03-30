package com.github.walkgs.springtests.services.exceptions;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5311841374579047519L;

    public EntityNotFoundException(String message) {
        super(message);
    }

}
