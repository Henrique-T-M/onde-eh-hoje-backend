package com.maybenot.oeh.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String resource, String id) {
        super(resource + " with " + id + " not found!");
    }
}
