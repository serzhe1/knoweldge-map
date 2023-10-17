package ru.serzhe1.knowledgemap.web.controller.advice.exception;

public class AppForbidden extends RuntimeException {
    public AppForbidden(String message) {
        super(message);
    }
}