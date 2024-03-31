package com.github.walkgs.springtests.resources.exceptions;

import com.github.walkgs.springtests.utils.Applicable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleError implements Serializable, Applicable<SimpleError> {

    @Serial
    private static final long serialVersionUID = 184857743348437316L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
