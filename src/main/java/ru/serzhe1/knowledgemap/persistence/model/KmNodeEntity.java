package ru.serzhe1.knowledgemap.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name = "map")
@Data
public class KmNodeEntity {
    @Id
    @SequenceGenerator(name = "map_seq", sequenceName = " map_seq ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "map_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "key", nullable = false)
    private String key;
    @Column(name = "parent")
    private Long parent;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;
    @Column(name = "update_date")
    private Timestamp updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version")
    private VersionEntity version;
}
