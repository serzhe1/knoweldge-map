package ru.serzhe1.knowledgemap.web.controller.advice.exception;

public class AppNotFoundException extends RuntimeException {
    public AppNotFoundException(String message) {
        super(message);
    }
}