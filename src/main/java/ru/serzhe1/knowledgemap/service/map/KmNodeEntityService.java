package ru.serzhe1.knowledgemap.service.map;

import org.springframework.transaction.annotation.Transactional;
import ru.serzhe1.knowledgemap.persistence.model.KmNodeEntity;

import java.util.List;

public interface KmNodeEntityService {

    KmNodeEntity findNodeById(Long id);

    List<KmNodeEntity> findNodeByKeyAndStatus(String key, Long versionId);
}
