package ru.serzhe1.knowledgemap.web.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
}

