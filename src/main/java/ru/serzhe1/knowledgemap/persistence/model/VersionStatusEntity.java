package ru.serzhe1.knowledgemap.persistence.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "version_status")
@Data
public class VersionStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private String code;
    private String name;
}