package com.mjc.school.repository.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TitleLengthException extends Exception {

    public TitleLengthException(String message) {
        super(message);
    }
}
