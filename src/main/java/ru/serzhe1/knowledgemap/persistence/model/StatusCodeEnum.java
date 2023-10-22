package ru.serzhe1.knowledgemap.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    WORK("Рабочая версия"),
    PUBLISH("Опубликованная версия"),
    ARCHIVE("Архиваня версия");

    private final String status;
}
