package com.app.famousprogrammer.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyException extends RuntimeException {
    private String message;
}
