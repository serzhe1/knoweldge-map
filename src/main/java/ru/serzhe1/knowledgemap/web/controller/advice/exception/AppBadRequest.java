package ru.serzhe1.knowledgemap.web.controller.advice.exception;
public class AppBadRequest extends RuntimeException {
    public AppBadRequest(String message) {
        super(message);
    }
}