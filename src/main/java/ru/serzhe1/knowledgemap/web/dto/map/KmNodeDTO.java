package ru.serzhe1.knowledgemap.web.dto.map;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmNodeDTO {
    private Long id;
    private String key;
    private Long parent;
    private String name;
    private Timestamp createDate;
    private Timestamp updateTime;
    private boolean isLeaf;
    private Set<KmNodeDTO> children;
}
