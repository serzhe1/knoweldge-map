package ru.serzhe1.knowledgemap.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name = "versions")
@Data
public class VersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp createDate;
    private Timestamp publishedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status")
    private VersionStatusEntity status;
}
