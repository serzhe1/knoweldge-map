package ru.serzhe1.knowledgemap.web.controller.advice.exception;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String msg) {
        super(msg);
    }
}
