package ru.asayke.houses.util;

import lombok.AllArgsConstructor;
import lombok.Data;

//TODO @Data
@Data
@AllArgsConstructor
public class UserExceptionResponse {
    private String message;
    private long timestamp;
}